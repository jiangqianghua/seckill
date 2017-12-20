package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecuteion;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

/**
 * 业务接口：  方法定义粒度控制 ，参数明确（不要只是map），返回类型（异常）
 * @author jiangqianghua
 *
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀接口记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启是输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecuteion executeSeckill(long seckillId , long userPhone , String md5)
	throws SeckillException ,RepeatKillException,SeckillCloseException;
	
	/**
	 * 执行秒杀操作  存储过程执行
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecuteion executeSeckillProcedure(long seckillId , long userPhone , String md5);
}
