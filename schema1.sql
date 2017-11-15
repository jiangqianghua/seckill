-- drop database seckill
CREATE database seckill;
use seckill ;
CREATE TABLE seckill(
	`seckill_id` bigint NOT NULL AUTO_INCREMENT ,
	`name` varchar(120) NOT NULL ,
	`number` int NOT NULL  ,
	`start_time` timestamp NOT NULL  ,
	`end_time` timestamp NOT NULL  ,
	`create_time` timestamp NOT NULL ,
	PRIMARY KEY(seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
	
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 ;

insert into seckill(name,number,start_time,end_time,create_time)
values
('1000元秒杀iphone6',100,'2016-05-08 00:00:00','2016-05-09 00:00:00','2016-05-01 00:00:00'),
('500元秒杀ipad2',200,'2016-05-08 00:00:00','2016-05-09 00:00:00','2016-05-01 00:00:00'),
('200元秒杀小米4',300,'2016-05-08 00:00:00','2016-05-09 00:00:00','2016-05-01 00:00:00'),
('200元秒杀红米',400,'2016-05-08 00:00:00','2016-05-09 00:00:00','2016-05-01 00:00:00');

create table success_killed(
	`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
	`user_phone` bigint NOT NULL COMMENT '用户手机号',
	`state`	tinyint NOT NULL DEFAULT -1 COMMENT '状态表示：-1：无效 0：成功 1：已付款',
	`create_time` timestamp NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id , user_phone),/*联合主键*/
	key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='描述成功明细表';
