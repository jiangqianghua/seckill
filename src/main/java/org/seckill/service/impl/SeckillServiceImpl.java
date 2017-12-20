package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecuteion;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service  //@Component @Service  @Dao @Controll
public class SeckillServiceImpl  implements SeckillService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 依赖注入
	@Autowired
	private SeckillDao seckillDao ; 
	
	@Autowired
	private SuccessKilledDao successKilledDao ;
	
	@Autowired
	private RedisDao redisDao ;
	
	// MD5盐值，用与混淆md5
	private final String salt = "ufgyafqwiur18723432r1928r@!#$%^$&!$!@$!@$WFSDXGVBSDSDGRWE";

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// 优化点，缓存优化
		// 访问redis
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null){
			//  访问数据库
			seckill = getById(seckillId);
			if(seckill == null){
				return new Exposer(false, seckillId);
			}else{
				// 放入redis
				redisDao.putSeckill(seckill);
			}
		}
		
		if(seckill == null)
		{
			return new Exposer(false,seckillId);
		}
		Date startTime = seckill.getStartTime() ; 
		Date endTime = seckill.getEndTime();
		//  系统时间
		Date nowTime = new Date() ;
		if(nowTime.getTime() < startTime.getTime() ||
				nowTime.getTime() > endTime.getTime())
		{
			return new Exposer(false,seckillId ,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		}
		String md5 = getMD5(seckillId) ;
		return new Exposer(true,md5, seckillId);
	}
	/**
	 * 使用注解控制事务方法的优点
	 * 1 开发团队达成一致，明确标注事务方法的编程风格
	 * 2 保证事务方法的执行时间尽可能短，不要穿插其他的网络的操作，或者剥离到事务方法外部
	 * 3 不是所有方法都要事务，只有一条修改，只是读操作不需要操作
	 */
	@Transactional
	public SeckillExecuteion executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		// 优化点，先购买，在更新，可以优化一般的网络延迟和GC操作
		
		if(md5 == null || !md5.equals(getMD5(seckillId)))
		{
			throw new SeckillException(SeckillStatEnum.DATA_REWRITE.getStateInfo());
		}
		//  执行秒杀行为，减库存 + 购买记录
		Date nowTime = new Date();
		try{
			
		//  记录购买行为
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if(insertCount <= 0)
			{
				throw new RepeatKillException(SeckillStatEnum.REPEAT_KILL.getStateInfo());
			}
			else
			{
				int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
				if(updateCount <=0 )
				{
					throw new SeckillCloseException(SeckillStatEnum.END.getStateInfo());
				}
				else
				{
					// 秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecuteion(seckillId,SeckillStatEnum.SUCCESS );
				}
			}
			
		}catch (SeckillCloseException e) {
			throw e ;
		}
		catch (RepeatKillException e) {
			throw e ;
		}
		catch(Exception e)
		{
			// 如果有异常，系统会自动帮我们回滚
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill inner error;"+e.getMessage());
		}
	}
	
//	/**
//	 * 使用注解控制事务方法的优点
//	 * 1 开发团队达成一致，明确标注事务方法的编程风格
//	 * 2 保证事务方法的执行时间尽可能短，不要穿插其他的网络的操作，或者剥离到事务方法外部
//	 * 3 不是所有方法都要事务，只有一条修改，只是读操作不需要操作
//	 */
//	@Transactional
//	public SeckillExecuteion executeSeckill(long seckillId, long userPhone,
//			String md5) throws SeckillException, RepeatKillException,
//			SeckillCloseException {
//		
//		if(md5 == null || !md5.equals(getMD5(seckillId)))
//		{
//			throw new SeckillException(SeckillStatEnum.DATA_REWRITE.getStateInfo());
//		}
//		//  执行秒杀行为，减库存 + 购买记录
//		Date nowTime = new Date();
//		try{
//			int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
//			if(updateCount <=0 )
//			{
//				throw new SeckillCloseException(SeckillStatEnum.END.getStateInfo());
//			}
//			else
//			{
//				//  记录购买行为
//				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
//				if(insertCount <= 0)
//				{
//					throw new RepeatKillException(SeckillStatEnum.REPEAT_KILL.getStateInfo());
//				}
//				else
//				{
//					// 秒杀成功
//					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
//					return new SeckillExecuteion(seckillId,SeckillStatEnum.SUCCESS );
//				}
//			}
//		}catch (SeckillCloseException e) {
//			throw e ;
//		}
//		catch (RepeatKillException e) {
//			throw e ;
//		}
//		catch(Exception e)
//		{
//			// 如果有异常，系统会自动帮我们回滚
//			logger.error(e.getMessage(),e);
//			throw new SeckillException("seckill inner error;"+e.getMessage());
//		}
//	}
	//获取md5
	private String getMD5(long seckillId)
	{
		String  base = seckillId + "/"+seckillId ;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5 ;
	}
	
	public SeckillExecuteion executeSeckillProcedure(long seckillId,
			long userPhone, String md5) {
		// TODO Auto-generated method stub
		return null;
	}


}
