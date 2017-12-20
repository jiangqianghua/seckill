package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis缓存
 * @author jiangqianghua
 *
 */
public class RedisDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private  JedisPool jedisPool = null;
	
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	public RedisDao(String ip , int port){
		jedisPool = new JedisPool(ip, port);
	}
	
	public Seckill getSeckill(long seckillId){
		
		//  创建空对象
		Seckill seckill = null ;
		try{
			Jedis jedis = jedisPool.getResource();
			
			try{
				String key = "seckill:"+seckillId ;
				// 将对象系列化，使用工具
				byte[] bytes = jedis.get(key.getBytes());
				// 判断是否重缓存中获取到数据
				if(bytes != null)
				{
					//  创建空对象
					seckill = schema.newMessage() ;
					// 执行反系列化
					ProtobufIOUtil.mergeFrom(bytes, seckill, schema);
				}
			}finally
			{
				jedis.close() ;
			}
			
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return seckill ;
	}
	/**
	 * 把对象存到缓存中
	 * @param seckill
	 * @return
	 */
	public String putSeckill(Seckill seckill){
		
		String result = null ;
		try{
			Jedis jedis = jedisPool.getResource();
			
			try{
				String key = "seckill:"+seckill.getSeckillId();
				System.out.println("key:"+key);
				byte[] bytes = ProtobufIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				int timeout = 60*60 ;
				result = jedis.setex(key.getBytes(), timeout, bytes);
				
			}finally
			{
				jedis.close() ;
			}
			
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return result;
		
	}
	
	
}
