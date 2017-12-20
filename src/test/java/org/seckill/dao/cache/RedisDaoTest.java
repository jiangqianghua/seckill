package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和juint整合，junit启动加载springIOC容器
 * @author jiangqianghua
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
	
	private long id = 1001L;
	@Autowired
	private RedisDao redisDao ;
	
	@Autowired
	private SeckillDao seckillDao ;
	
	@Test
	public void testSeckill() throws Exception{
		Seckill seckill = redisDao.getSeckill(id);
		if(seckill == null ){
			seckill = seckillDao.queryById(id);
			if(seckill != null){
				String  result = redisDao.putSeckill(seckill);
				System.out.println("put result:"+result);
				seckill = redisDao.getSeckill(id);
				System.out.println("get redis:"+seckill);
			}
			
		}
	}
}
