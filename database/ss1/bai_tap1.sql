create database bai_tap1;
use bai_tap1;
create table class(
	id int primary key,
    `name` varchar(100) not null
);
create table teacher(
	id int primary key,
    `name` varchar(100) not null,
    age int,
    country varchar(100)
);