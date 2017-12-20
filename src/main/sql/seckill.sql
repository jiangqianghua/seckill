--- 秒杀执行存储过程
DELIMITER $$;  --sonsole ;转换 $$
-- 定义存储过程
-- row_count 返回上一条修改类型sql(delete ,insert,update)的行数
-- insert_count 0 未修改数据  >0 修改的行数 <0  错误/未执行sql
CREATE PROCEDURE `seckill`.`execute_seckill`
(IN v_seckill_id BIGINT,IN v_phone BIGINT,
IN v_kill_time TIMESTAMP,OUT r_result INT)
	BEGIN
		DECLARE insert_count INT DEFAULT 0;
		START TRANSACTION;
		INSERT IGNORE INTO success_killed
			(seckill_id,user_phone,create_time)
			VALUES(v_seckill_id,v_phone,v_kill_time);
		SELECT ROW_COUNT() INTO insert_count;
		IF(insert_count = 0)THEN
			ROLLBACK;
			SET r_result = -1 ;
		ELSE IF(insert_count < 0)THEN
			ROLLBACK;
			SET r_result = -2;
		ELSE 
			UPDATE seckill
			SET number = number - 1
			WHERE seckill_id = v_seckill_id
				AND end_time > v_kill_time
				AND start_time < v_kill_time
				AND number > 0 ;
			SELECT ROW_COUNT() INTO insert_count;
			IF(insert_count = 0) THEN
				ROLLBACK;
				SET r_result = 0 ;
			ELSE IF (insert_count < 0) THEN
				ROLLBACK;
				SET r_result = -2; 
			ELSE 
				COMMIT ;
				SET r_result = 1 ;
			END IF 
		END IF;
	END ;
$$  
--  $$表示存储过程定义结束

-- test 执行存储过程
DELIMITER ; --  转换$$  为默认
set @r_result = -3 ; 
-- 执行存储过程
call execute_seckill(1003,12345678912,now(),@r_result);
-- 获取结果
select @r_result;


-----存储过程
-----1存储过程优化  事务行级锁持有的时间
-----2不要过度依赖存储过程
-----3简单的逻辑可以应用存储过程
-----4 QPS:一个秒杀6000/qps

		