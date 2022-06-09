create table if not exists country(
    id int(4) primary key not null auto_increment,
    name varchar(20) not null,
    english_name varchar(100),
    island varchar(20) not null,
    language varchar(20) not null,
    population int,
    grown_time varchar(10));

alter table country auto_increment = 1000;
alter table country add code varchar(10) not null comment '国家代码';

insert into country (name,english_name,island,language,population,grown_time) values('中华人民共和国','People\\'s Republic of China','亚洲','中文',1400000000,' 19491001');

ALTER TABLE demo.country MODIFY COLUMN grown_time DATE NULL;
ALTER TABLE demo.country MODIFY COLUMN id BIGINT NOT NULL;