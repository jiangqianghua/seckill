package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("seckillId") long seckillId ,@Param("killTime")  Date killTime);
	/**
	 * 查询库存
	 * @param secKillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 查询所有商品接口
	 * @param offet
	 * @param limit
	 * @return
	 */
	List queryAll(@Param("offset") int offset , @Param("limit") int limit) ;
	
}
