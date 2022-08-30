drop table if exists user;
create table user
(
    user_id int(10) not null auto_increment comment '用户id',
	role_id int(10) null default null comment '角色id',
	authority_id int(10) null default null comment '权限id',
	username varchar(255) not null comment '用户名',
	password varchar(255) not null comment '密码',
	account_non_expired bit(1) not null comment '账号是否到期',
	account_non_locked bit(1) not null comment '账号是否锁定',
	credentials_non_expired bit(1) not null comment '密码是否过期',
	enabled bit(1) not null comment '账号是否启用',
primary key (user_id)
	using BTREE
);

drop table IF EXISTS role;
create TABLE role
(
    role_id int(10) NOT NULL AUTO_INCREMENT comment '角色id',
    role_name varchar(255) NOT NULL comment '角色名称',
    authority_id int(10) null default null comment '权限id',
    enabled bit(1) not null comment '角色是否启用',
    PRIMARY KEY (role_id) USING BTREE
);



DROP TABLE IF EXISTS authority;
CREATE TABLE authority
(
    authority_id int(10) NOT NULL AUTO_INCREMENT COMMENT '资源id',
    authority varchar(255) NULL DEFAULT NULL COMMENT '资源名称',
    code varchar(255) NULL DEFAULT NULL COMMENT '资源代码',
    url  varchar(255) NULL DEFAULT NULL COMMENT '资源路径',
    enabled bit(1) not null COMMENT '资源是否启用',
    PRIMARY KEY (authority_id) USING BTREE
);

create table persistent_logins (
  	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
)

insert into role(role_name,enabled) values ('ADMIN',1);
insert into role(role_name,enabled) values ('USER',1);

insert into user(role_id,username,password,account_non_expired,account_non_locked,credentials_non_expired,enabled) values(1,'admin','$2a$10$tReJ0hvhYophaqfFDLF/rOmhgdbtCKnynOxSzjuupYbwuG5XyzVdW',1,1,1,1);
insert into user(role_id,username,password,account_non_expired,account_non_locked,credentials_non_expired,enabled) values(2,'user','$2a$10$tReJ0hvhYophaqfFDLF/rOmhgdbtCKnynOxSzjuupYbwuG5XyzVdW',1,1,1,1);
