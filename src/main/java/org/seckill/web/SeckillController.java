package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecuteion;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.seckill.service.impl.SeckillServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//http://127.0.0.1:8081/seckill/list
@Controller
@RequestMapping("/seckill") //url:/模块/资源/{id}/细分   /seckill/list
public class SeckillController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	private SeckillService seckillService ;
	// get请求  /seckill/list
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model)
	{
		// 获取列表页
		List<Seckill> list = seckillService.getSeckillList() ;
		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list" ;  // ==  ///Web-INF/jsp/"list".jsp
	}
	
	@RequestMapping(value="{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model)
	{
		if(seckillId == null)
		{
			//  请求重定向
			return "redirect:/seckill/list";
		}
		
		Seckill seckill = seckillService.getById(seckillId);
		if(seckill == null)
		{
			//  请求转发
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail" ;  // ==  ///Web-INF/jsp/"detail".jsp
		
	}
	
	//// 以下是ajax接口
	  @RequestMapping(value = "{seckillId}/exposer",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){
	        SeckillResult<Exposer> result;
	        try{
	            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
	            result =  new SeckillResult<Exposer>(true,exposer);
	        }catch (Exception e){
	        	logger.error(e.getMessage(),e);
	            result = new SeckillResult<Exposer>(false,e.getMessage());
	        }
	        return result;
	    }
	
	  /**
	     * 秒杀执行方法.
	     * @param seckillId 秒杀商品ID
	     * @param userPhone 秒杀用户手机
	     * @param md5 秒杀Key
	     * @return
	     */
	    @RequestMapping(value = "{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public SeckillResult<SeckillExecuteion> execute(@PathVariable("seckillId")Long seckillId,
	                                                   @CookieValue(value = "userPhone",required = false)Long userPhone,
	                                                   @PathVariable("md5")String md5){
	        SeckillResult<SeckillExecuteion> result;
	        SeckillExecuteion seckillExecution;

	        if (userPhone == null){
	            result = new SeckillResult<SeckillExecuteion>(false,"未注册");
	        }else {
	            try{
	                seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
	                result =  new SeckillResult<SeckillExecuteion>(true,seckillExecution);
	            }catch (SeckillCloseException e){
	                seckillExecution = new SeckillExecuteion(seckillId, SeckillStatEnum.END);
	                result = new SeckillResult<SeckillExecuteion>(true,seckillExecution);
	            }catch (RepeatKillException e){
	                seckillExecution = new SeckillExecuteion(seckillId, SeckillStatEnum.REPEAT_KILL);
	                result = new SeckillResult<SeckillExecuteion>(true,seckillExecution);
	            }catch (Exception e){
	            	logger.error(e.getMessage(),e);
	                seckillExecution = new SeckillExecuteion(seckillId, SeckillStatEnum.INNER);
	                result = new SeckillResult<SeckillExecuteion>(true,seckillExecution);
	            }
	        }

	        return result;
	    }
	
	@RequestMapping(value="time/now",method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time()
	{
		Date now = new Date();
		return new SeckillResult<Long>(true , now.getTime());
	}
}
