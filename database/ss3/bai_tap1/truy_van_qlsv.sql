-- câu 1 --
 select *from student
 where student_name like 'H%';
 
 -- câu 2 --
 select *from class
 where month(start_date) = 12;
 
 -- câu 3 --
 select *from subject	
 where credit between 3 and 5;
 
 -- câu 4 --
 -- bật tắt chế độ an toàn --
 set SQL_SAFE_UPDATES = 0;
 update student
 set class_id = 2
 where student_name = 'Hung';
 set SQL_SAFE_UPDATES = 1;

-- câu 5 --
select s.student_name, sub.sub_name, m.mark from student s
join mark m on s.student_id = m.student_id
join subject sub on m.sub_id = sub.sub_id
order by m.mark desc, s.student_name asc;


