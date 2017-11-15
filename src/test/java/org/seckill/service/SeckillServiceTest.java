package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecuteion;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		private	SeckillService seckillService ;
		@Test
		public void testGetSeckillList()
		{
			List<Seckill> list = seckillService.getSeckillList();
			logger.info("list={}",list);
		}
		//@Test
		public void testGetById()
		{
			long id = 1000L;
			Seckill seckill = seckillService.getById(id);
			logger.info("seckill={}",seckill);
		}
		//@Test
		public void testExportSeckillUrl() throws Exception
		{
			long id = 1000L;
			Exposer exposer = seckillService.exportSeckillUrl(id);
			logger.info("exposer={}",exposer);
			//15:34:27.348 [main] INFO  o.seckill.service.SeckillServiceTest - exposer=exposed=true md5=167f194e58b0df0f14ab95f25d42a071  seckillId=1000 now=0 start=0 end=0
		}
		
		//@Test
		public void testExecuteSeckill() throws Exception
		{
			long id = 1000L;
			long phone = 15801523722L ;
			String md5 = "167f194e58b0df0f14ab95f25d42a071";
			try
			{
				SeckillExecuteion seckillExecuteion = seckillService.executeSeckill(id, phone, md5);
				logger.info("seckillExecuteion={}",seckillExecuteion);
			}catch (RepeatKillException e) {
				logger.error(e.getMessage(),e);
			}
			
		}
		
		//  集成测试
		//@Test
		public void testSeckillLogic()
		{
			long id = 1000L;
			Exposer exposer = seckillService.exportSeckillUrl(id);
			logger.info("exposer={}",exposer);
			
			if(exposer != null && exposer.isExposed())
			{
				long phone = 15801523723L ;
				String md5 = exposer.getMd5();
				try
				{
					SeckillExecuteion seckillExecuteion = seckillService.executeSeckill(id, phone, md5);
					logger.info("seckillExecuteion={}",seckillExecuteion);
				}catch (RepeatKillException e) {
					logger.error(e.getMessage(),e);
				}catch (SeckillCloseException e) {
					logger.error(e.getMessage(),e);
				}
			}
			else
			{
				logger.warn("exposer={}",exposer);
			}
		}
}
