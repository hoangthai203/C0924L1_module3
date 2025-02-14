create database if not exists m3_c0924l1_jv103;
use m3_c0924l1_jv103;
create table jame (
 `username` varchar(50) primary key,
 `password` varchar(50)
);
create table class (
 id int primary key auto_increment,
 `name` varchar(50)
);
create table room (
 id int primary key auto_increment,
 `name` varchar(50),
 class_id int,
 foreign key(class_id) references class(id)
);
create table student(
 id int primary key auto_increment,
 `name` varchar(50),
 gender boolean,
 birthday date,
 email varchar(100),
 point float,
 `username` varchar(50) unique,
 class_id int,
 foreign key (`username`) references jame(`username`),
 foreign key (class_id) references class(id)
 );
 
create table instructor (
 id int primary key auto_increment,
 `name` varchar(50),
 birthday date,
 salary float
 );
 create table instructor_class (
  instructor_id int,
  class_id int,
  start_time date,
  end_time date,
  primary key (instructor_id,class_id),
  foreign key (instructor_id) references instructor(id),
  foreign key (class_id) references class(id)
 );
 
 -- input data
insert into class (name) values ('c1121g1'), ('c1221g1'),('a0821i1'),('a0921i1');
insert into room(name,class_id) values ('Ken',1), ('Jame',1),('Ada',2),('Andy',2);

insert into jame(`username`,`password`)
 values('cunn','12345'),('chunglh','12345'),('hoanhh','12345'),('dungd','12345'),('huynhtd','12345'),
 ('hainm','12345'),('namtv','12345'),('hieuvm','12345'),('kynx','12345'),('vulm','12345');

insert into jame(`username`,`password`)
 values('chau','12345');
 
insert into instructor(`name`,birthday, salary)
 values('tran van chanh','1985-02-03',100),('tran minh chien','1985-02-03',200),('vu thanh tien','1985-02-03',300);
insert into instructor(`name`,birthday, salary)
 values('tran van nam','1989-12-12',100);


 
 insert into student(`name`,birthday, gender,`point`, class_id,`username`) 
 values ('nguyen ngoc cu','1981-12-12',1,8,1,'cunn'),('le hai chung','1981-12-12',1,5,1,'chunglh'),
 ('hoang huu hoan','1990-12-12',1,6,2,'hoanhh'),('dau dung','1987-12-12',1,8,1,'dungd'),
 ('ta dinh huynh','1981-12-12',1,7,2,'huynhtd'),('nguyen minh hai','1987-12-12',1,9,1,'hainm'),
 ('tran van nam','1989-12-12',1,4,2,'namtv'),('vo minh hieu','1981-12-12',1,3,1,'hieuvm'),
 ('le xuan ky','1981-12-12',1,7,2,'kynx'),('le minh vu','1981-12-12',1,7,1,'vulm');

  insert into student(`name`,birthday, gender,`point`, class_id) 
 values ('nguyen van a','1981-12-12',1,8,null),('tran van b','1981-12-12',1,5,null);
 
--   insert into student(`name`,birthday, gender,`point`, class_id,`username`) 
--  values ('nguyen minh hai chau','1981-12-12',1,8,null,'chau');
 
 insert into instructor_class(class_id,instructor_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(3,2);

select * from room;
select* from class;
select* from student;
select* from jame;
select* from instructor;
select * from instructor_class;
