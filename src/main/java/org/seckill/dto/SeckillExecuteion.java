package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 秒杀执行后的操作结果
 * @author jiangqianghua
 *
 */
public class SeckillExecuteion {

	private  long seckillId ; 
	
	//  秒杀执行状态
	private int state ;
	
	// 状态描述
	private String stateInfo ;
	
	//  秒杀成功对象
	private SuccessKilled successKilled ;

	
	public SeckillExecuteion(long seckillId, SeckillStatEnum statEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	

	public SeckillExecuteion(long seckillId, SeckillStatEnum statEnum) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}



	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	@Override
	public String toString() {
		return " seckillId="+seckillId+" state="+state+" stateInfo="+stateInfo 
		+" successKilled="+successKilled;
	}
	
	
}


	

