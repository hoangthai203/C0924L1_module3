use quan_ly_sinh_vien;
-- câu 1  Hiển thị tất cả các thông tin môn học có credit lớn nhất --
select * from subject	
where credit = (select max(credit) from subject);

-- câu 2 Hiển thị các thông tin môn học có điểm thi lớn nhất --
select sub.* from subject sub
join mark m on sub.sub_id = m.sub_id
where m.mark = (select max(mark) from mark);

-- câu 3 Hiển thị thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần --
select s.student_id, s.student_name, s.address, s.phone, avg(m.mark) as avg_score
from student s
join mark m on s.student_id = m.student_id
group by s.student_id
order by avg_score desc;
