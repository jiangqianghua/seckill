package org.seckill.dao;



import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
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
public class SeckillDaoTest {

	//  注入Dao实现类依赖
	@Resource
	private SeckillDao seckillDao ;
	@Test
	public void testReduceNumber() throws Exception
	{
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println("updateCount = " + updateCount);
	}
	
	@Test
	public void testQueryById() throws Exception
	{
//		long id = 1000;
//		Seckill seckill = seckillDao.queryById(id);
//		System.out.println(seckill);
//		System.out.println("-----------");
	}
	
	@Test
	public void testQueryAll() throws Exception
	{
//		java.util.List<Seckill> seckills = (java.util.List<Seckill>) seckillDao.queryAll(0, 5);
//		for (Seckill seckill : seckills) {
//			System.out.println(seckill);
//		}
	}

}
