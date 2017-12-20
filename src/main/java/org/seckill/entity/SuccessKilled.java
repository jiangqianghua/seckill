package org.seckill.entity;

import java.util.Date;

public class SuccessKilled {

	private long seckillId ; 
	private long userPhone ; 
	private short state ; 
	private Date createTime ;
	
	private Seckill secKil;
	
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Seckill getSecKil() {
		return secKil;
	}
	public void setSecKil(Seckill secKil) {
		this.secKil = secKil;
	}
	@Override
	public String toString() {
		return " seckillId="+seckillId +" userPhone="+userPhone;
	}
	
}
