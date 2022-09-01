drop table if exists user;
create table user
(
    user_id int not null auto_increment COMMENT '用户id',
	username varchar(255) not null COMMENT '用户名',
	password varchar(255) not null COMMENT '密码',
	account_non_expired bit(1) not null COMMENT '账号是否到期',
	account_non_locked bit(1) not null COMMENT '账号是否锁定',
	credentials_non_expired bit(1) not null COMMENT '密码是否过期',
	enabled bit(1) not null COMMENT '账号是否启用',
	primary key (user_id) using BTREE
);

DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    role_id   int NOT NULL AUTO_INCREMENT COMMENT '角色id',
    role_name varchar(255) NOT NULL COMMENT '角色名称',
    enabled bit(1) not null COMMENT '角色是否启用',
    PRIMARY KEY (role_id) USING BTREE
);

DROP TABLE IF EXISTS authority;
CREATE TABLE authority
(
    authority_id int NOT NULL AUTO_INCREMENT COMMENT '资源id',
    code varchar(255) NULL DEFAULT NULL COMMENT '资源代码',
    url  varchar(255) NULL DEFAULT NULL COMMENT '资源路径',
    description varchar(255) NULL DEFAULT NULL COMMENT '资源描述',
    enabled bit(1) not null COMMENT '资源是否启用',
    PRIMARY KEY (authority_id) USING BTREE
);

create table persistent_logins (
  	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
)

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    id      int NOT NULL AUTO_INCREMENT,
    user_id int NULL DEFAULT NULL,
    role_id int NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE
) ;

DROP TABLE IF EXISTS user_authority;
CREATE TABLE user_authority
(
    id      int NOT NULL AUTO_INCREMENT,
    user_id int NULL DEFAULT NULL,
    authority_id int NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE
) ;

DROP TABLE IF EXISTS role_authority;
CREATE TABLE role_authority
(
    id      int NOT NULL AUTO_INCREMENT,
    role_id int NULL DEFAULT NULL,
    authority_id int NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE
) ;
insert into authority(description,code,url,enabled) values('管理员测试权限','ADMIN_TEST','/admin/test',1);
insert into authority(description,code,url,enabled) values('普通用户测试权限','USER_TEST','/user/username',1);
insert into authority(description,code,url,enabled) values('特殊测试权限','PRIVATE_TEST','/private/test',1);
insert into authority(description,code,url,enabled) values('公共测试权限','COMMON_TEST','/common/test',1);

insert into role(role_name,enabled) values ('ADMIN',1);
insert into role(role_name,enabled) values ('USER',1);

insert into user(role_id,username,password,account_non_expired,account_non_locked,credentials_non_expired,enabled) values(1,'admin','$2a$10$tReJ0hvhYophaqfFDLF/rOmhgdbtCKnynOxSzjuupYbwuG5XyzVdW',1,1,1,1);
insert into user(role_id,username,password,account_non_expired,account_non_locked,credentials_non_expired,enabled) values(2,'user','$2a$10$tReJ0hvhYophaqfFDLF/rOmhgdbtCKnynOxSzjuupYbwuG5XyzVdW',1,1,1,1);

insert into user_role (user_id,role_id) values(1,1);
insert into user_role (user_id,role_id) values(2,2);

insert into role_authority(role_id,authority_id) values(1,1);
insert into role_authority(role_id,authority_id) values(1,2);
insert into role_authority(role_id,authority_id) values(2,2);

insert into user_authority(user_id,authority_id) values(2,3);