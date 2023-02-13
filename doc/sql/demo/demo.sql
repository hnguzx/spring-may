drop table country;
create table if not exists country (
    id bigint primary key not null comment '主键',
    name varchar(20) not null comment '国家名称',
    english_name varchar(100) comment '国家英文名称',
    island varchar(20) not null comment '国家所属大陆',
    language varchar(20) not null comment '国家官方语言',
    population int comment '人口数量',
    grown_date varchar(10) comment '国家成立日期',
    code varchar(10) not null comment '国家代码'
   );
