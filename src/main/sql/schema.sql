-- 数据库初始化脚本
-- 创建数据库
CREATE database seckill;
--使用数据库
use seckill ;
-- 创建秒杀库存表
CREATE TABLE seckill(
	'seckill_id' bigint NOT null AUTO_INCREMENT '商品库存id',
	'name' varchar(120) NOT NULL COMMENT '商品名称',
	'number' int NOT NULL COMMENT '库存数量'
	'start_time' timestamp NOT NULL COMMENT '秒杀开始时间',
	'end_time' timestamp NOT NULL COMMENT '秒杀结束时间',
	'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
	
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf-8 COMMENT='秒杀库存表'

--初始化数据
insert inti seckill(name,number,start_time,end_time)
values
('1000元秒杀iphone6',100,'2016-05-08 00:00:00','2016-05-09 00:00:00'),
('500元秒杀ipad2',200,'2016-05-08 00:00:00','2016-05-09 00:00:00'),
('200元秒杀小米4',300,'2016-05-08 00:00:00','2016-05-09 00:00:00'),
('200元秒杀红米',400,'2016-05-08 00:00:00','2016-05-09 00:00:00'),

-- 描述成功明细表
-- 用户登录认证相关信息
create table success_killed(
	'seckill_id' bitint NOT NULL COMMENT '秒杀商品id',
	'user_phone' bitint NOT NULL COMMENT '用户手机号',
	'state'	tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1：无效 0：成功 1：已付款',
	'create_time' timestamp NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id , user_phone),/*联合主键*/
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf-8 COMMENT='描述成功明细表'
