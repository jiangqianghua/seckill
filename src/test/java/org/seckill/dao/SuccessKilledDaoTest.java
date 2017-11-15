package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
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
public class SuccessKilledDaoTest {
	//  注入Dao实现类依赖
	@Resource
	private SuccessKilledDao successKilledDao ;

	
	@Test
	public void testInsertSuccessKilled() throws Exception
	{
//		long id = 1000L;
//		long phone = 15801523721L ;
//		int resultCount = successKilledDao.insertSuccessKilled(id, phone);
//		System.out.println("resultCount="+resultCount);
	}
	
	@Test
	public void testQueryByIdWithSeckill() throws Exception
	{
		long id = 1000L;
		long phone = 15801523721L ;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println(successKilled);
	}
}
