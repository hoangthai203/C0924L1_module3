-- tao csdl--
create database c0924L1;
-- tao table student --
use c0924L1;
create table students (
id int primary key,
`name` varchar(100) not null,
`point` float
);
-- them dl vao bang --
insert into students(id,name,point) values(1,"thai1",6); 	
insert into students values(2,"thai2",8);
insert into students values(3,"thai3",7);
-- doc dl --
select * from students;
-- xoa bang --
drop table students;
-- update dl --
update students set name = 'thai33' where id = 3;
-- xoa theo id --
delete from students where id = 3;
-- xoa csdl --
drop database c0924l1;
-- end --