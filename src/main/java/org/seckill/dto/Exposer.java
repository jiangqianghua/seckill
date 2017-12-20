package org.seckill.dto;

/**
 * 暴露秒杀地址DTO
 * @author jiangqianghua
 *
 */
public class Exposer {
	
	// 是否开始秒杀
	private boolean exposed ;
	//  加密
	private String md5 ;
	//  秒杀id
	private long seckillId ; 
	//  系统时间
	private long now ; 
	// 秒杀开始时间
	private long start ;
	// 秒杀结束时间
	private long end ;
	
	public Exposer(boolean exposed , String md5 , long seckillId)
	{
		this.exposed = exposed ; 
		this.md5 = md5 ; 
		this.seckillId = seckillId ;
	}



	public Exposer(boolean exposed, long seckillId, long now, long start,
			long end) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}



	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "exposed="+exposed+" md5="+md5+" "+" seckillId="+seckillId+" now="+now
		+" start="+start+" end="+end;
	}



	/**
	 * @return the exposed
	 */
	public boolean isExposed() {
		return exposed;
	}



	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	
	
}
