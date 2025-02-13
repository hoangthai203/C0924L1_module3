create database if not exists ql_ban_hang;
use ql_ban_hang;
create table customer(
	cid int auto_increment primary key,
    cname varchar(50) not null default ("abc"),
    cage int(3)
);
create table `order`(
	oid int auto_increment primary key,
    cid int,
    odate date,
    ototalprice int,
    foreign key (cid) references customer(cid)
);
create table product(
	pid int auto_increment primary key,
    pname varchar(50),
    pprice int
);
create table orderDetail(
	oid int,
    pid int,
    primary key(oid, pid),
    foreign key(oid) references `order`(oid),
    foreign key(pid) references product(pid)
);