-- 1. Tạo database
create database if not exists quan_ly_khach_san;
use quan_ly_khach_san;

-- 2. Bảng vị trí
create table vi_tri (
    ma_vi_tri int primary key auto_increment,
    ten_vi_tri varchar(45) not null
);

-- 3. Bảng trình độ
create table trinh_do (
    ma_trinh_do int primary key auto_increment,
    ten_trinh_do varchar(45) not null
);

-- 4. Bảng bộ phận
create table bo_phan (
    ma_bo_phan int primary key auto_increment,
    ten_bo_phan varchar(45) not null
);

-- 5. Bảng nhân viên
create table nhan_vien (
    ma_nhan_vien int primary key auto_increment,
    ho_ten varchar(45) not null,
    ngay_sinh date,
    so_cmnd varchar(45) unique,
    luong double check (luong >= 0),
    so_dien_thoai varchar(45) unique,
    email varchar(45) unique,
    dia_chi varchar(45),
    ma_vi_tri int,
    ma_trinh_do int,
    ma_bo_phan int,
    foreign key (ma_vi_tri) references vi_tri(ma_vi_tri),
    foreign key (ma_trinh_do) references trinh_do(ma_trinh_do),
    foreign key (ma_bo_phan) references bo_phan(ma_bo_phan)
);

-- 6. Bảng loại khách
create table loai_khach (
    ma_loai_khach int primary key auto_increment,
    ten_loai_khach varchar(45) not null
);

-- 7. Bảng khách hàng
create table khach_hang (
    ma_khach_hang int primary key auto_increment,
    ma_loai_khach int,
    ho_ten varchar(45) not null,
    ngay_sinh date,
    gioi_tinh bit,
    so_cmnd varchar(45) unique,
    so_dien_thoai varchar(45) unique,
    email varchar(45) unique,
    dia_chi varchar(45),
    foreign key (ma_loai_khach) references loai_khach(ma_loai_khach)
);

-- 8. Bảng loại dịch vụ
create table loai_dich_vu (
    ma_loai_dich_vu int primary key auto_increment,
    ten_loai_dich_vu varchar(45) not null
);

-- 9. Bảng kiểu thuê
create table kieu_thue (
    ma_kieu_thue int primary key auto_increment,
    ten_kieu_thue varchar(45) not null
);

-- 10. Bảng dịch vụ
create table dich_vu (
    ma_dich_vu int primary key auto_increment,
    ten_dich_vu varchar(45) not null,
    dien_tich int check (dien_tich > 0),
    chi_phi_thue double check (chi_phi_thue >= 0),
    so_nguoi_toi_da int check (so_nguoi_toi_da > 0),
    ma_kieu_thue int,
    ma_loai_dich_vu int,
    tieu_chuan_phong varchar(45),
    mo_ta_tien_nghi_khac varchar(45),
    dien_tich_ho_boi double default 0,
    so_tang int default 1 check (so_tang > 0),
    foreign key (ma_kieu_thue) references kieu_thue(ma_kieu_thue),
    foreign key (ma_loai_dich_vu) references loai_dich_vu(ma_loai_dich_vu)
);

-- 11. Bảng hợp đồng
create table hop_dong (
    ma_hop_dong int primary key auto_increment,
    ngay_lam_hop_dong datetime not null,
    ngay_ket_thuc datetime not null,
    tien_dat_coc double check (tien_dat_coc >= 0),
    ma_nhan_vien int,
    ma_khach_hang int,
    ma_dich_vu int,
    foreign key (ma_nhan_vien) references nhan_vien(ma_nhan_vien),
    foreign key (ma_khach_hang) references khach_hang(ma_khach_hang),
    foreign key (ma_dich_vu) references dich_vu(ma_dich_vu)
);

-- 12. Bảng dịch vụ đi kèm
create table dich_vu_di_kem (
    ma_dich_vu_di_kem int primary key auto_increment,
    ten_dich_vu_di_kem varchar(45) not null,
    gia double check (gia >= 0),
    don_vi varchar(10),
    trang_thai varchar(45)
);

-- 13. Bảng hợp đồng chi tiết
create table hop_dong_chi_tiet (
    ma_hop_dong_chi_tiet int primary key auto_increment,
    ma_hop_dong int,
    ma_dich_vu_di_kem int,
    so_luong int check (so_luong > 0),
    foreign key (ma_hop_dong) references hop_dong(ma_hop_dong),
    foreign key (ma_dich_vu_di_kem) references dich_vu_di_kem(ma_dich_vu_di_kem)
);

-- nhập dữ liệu --

insert into vi_tri (ten_vi_tri) values 
('Quản Lý'),
('Nhân Viên');

insert into trinh_do (ma_trinh_do, ten_trinh_do) values
(1, 'Trung Cấp'),
(2, 'Cao Đẳng'),
(3, 'Đại Học'),
(4, 'Sau Đại Học');

insert into bo_phan (ma_bo_phan, ten_bo_phan) values
(1, 'Sale-Marketing'),
(2, 'Hành chính'),
(3, 'Phục vụ'),
(4, 'Quản lý');

insert into loai_khach (ma_loai_khach, ten_loai_khach) values
(1, 'Diamond'),
(2, 'Platinum'),
(3, 'Gold'),
(4, 'Silver'),
(5, 'Member');

insert into kieu_thue (ma_kieu_thue, ten_kieu_thue) values
(1, 'year'),
(2, 'month'),
(3, 'day'),
(4, 'hour');

insert into loai_dich_vu (ma_loai_dich_vu, ten_loai_dich_vu) values
(1, 'Villa'),
(2, 'House'),
(3, 'Room');

insert into nhan_vien (ma_nhan_vien, ho_ten, ngay_sinh, so_cmnd, luong, so_dien_thoai, email, dia_chi, ma_vi_tri, ma_trinh_do, ma_bo_phan) values
(1, 'Nguyễn Văn An', '1970-11-07', '456231786', 10000000, '0901234121', 'annguyen@gmail.com', '295 Nguyễn Tất Thành, Đà Nẵng', 1, 3, 1),
(2, 'Lê Văn Bình', '1997-04-09', '654231234', 7000000, '0943213214', 'binhlv@gmail.com', '22 Yên Bái, Đà Nẵng', 1, 2, 2),
(3, 'Hồ Thị Yến', '1995-12-12', '99231733', 8000000, '0932345678', 'thiyen@gmail.com', 'K234/11 Hải Phòng, Gia Lai', 2, 4, 3),
(4, 'Võ Công Toàn', '1980-04-04', '123432123', 9000000, '0374443232', 'toan0404@gmail.com', '77 Hoàng Diệu, Quảng Trị', 1, 3, 1),
(5, 'Nguyễn Bính Phát', '1999-12-09', '120321321', 6000000, '0932143121', 'phatphat@gmail.com', '294 Bạch Đằng, Đà Nẵng', 2, 1, 4),
(6, 'Khúc Nguyễn An Nghi', '2000-07-07', '098765432', 8500000, '0978653231', 'annghi120@gmail.com', '49 Nguyễn Kiều, Đà Nẵng', 2, 2, 2),
(7, 'Nguyễn Hữu Hà', '1993-01-03', '053412334', 8000000, '0941234121', 'nhh0101@gmail.com', '111 Hùng Vương, Hà Nội', 2, 3, 3),
(8, 'Nguyễn Hà Đồng', '1989-05-08', '098765323', 7500000, '0932123444', 'donghanguyen@gmail.com', '21 Trần Phú, Huế', 2, 4, 4),
(9, 'Tống Hoàng', '1982-09-03', '256781231', 7200000, '0254144441', 'hoangtong@gmail.com', '213 Hải Ninh, Nghệ An', 2, 3, 2),
(10, 'Nguyễn Công Đạo', '1994-01-08', '755434343', 7000000, '0987867411', 'nguyencongdao12@gmail.com', '6 Hoà Khánh, Đà Nẵng', 2, 3, 2);

insert into khach_hang (ma_khach_hang, ho_ten, ngay_sinh, gioi_tinh, so_cmnd, so_dien_thoai, email, dia_chi, ma_loai_khach) values
(1, 'Nguyễn Thị Hào', '1970-11-07', 0, '643431213', '0945243362', 'thihao07@gmail.com', '23 Nguyễn Hoàng, Đà Nẵng', 5),
(2, 'Phạm Xuân Diệu', '1992-08-08', 1, '865342132', '0954323333', 'xuandieu92@gmail.com', 'K77/22 Thái Phiên, Quảng Trị', 3),
(3, 'Trương Đình Nghệ', '1990-02-10', 1, '984312343', '0912345678', 'phenghan2702@gmail.com', '199 Hùng Ích Khiêm, Vinh', 1),
(4, 'Dương Văn Quan', '1981-07-08', 1, '543243111', '0954533111', 'duongquan@gmail.com', 'K453/12 Lê Lợi, Đà Nẵng', 1),
(5, 'Hoàng Trần Nhị Nhi', '1995-12-09', 0, '295654432', '031245678', 'nhinhi123@gmail.com', '224 Lý Thái Tổ, Gia Lai', 4),
(6, 'Tôn Nữ Mộc Châu', '1987-06-08', 0, '564321321', '0976543333', 'tonnuchau@gmail.com', 'K234/11 Hải Phòng, Đà Nẵng', 5),
(7, 'Nguyễn Mỹ Kim', '1994-01-07', 0, '965654321', '0943123456', 'kimcuong84@gmail.com', 'K123/45 Lê Lợi, Hồ Chí Minh', 2),
(8, 'Nguyễn Thị Hào', '1990-07-01', 0, '865342133', '0965433222', 'haohaog9@gmail.com', '198 Văn Linh, Kon Tum', 3),
(9, 'Trần Đại Danh', '1994-07-01', 1, '334432132', '0953124444', 'danhhai99@gmail.com', '29 Lý Thường Kiệt, Quảng Ngãi', 1),
(10, 'Nguyễn Tâm Đắc', '1997-01-01', 1, '343312432', '0987654321', 'dactam@gmail.com', '6 Hoà Khánh, Đà Nẵng', 1);

insert into dich_vu_di_kem (ma_dich_vu_di_kem, ten_dich_vu_di_kem, gia, don_vi, trang_thai) VALUES
(1, 'Karaoke', 10000, 'giờ', 'tiện nghi, hiện tại'),
(2, 'Thuê xe máy', 10000, 'chiếc', 'hỏng 1 xe'),
(3, 'Thuê xe đạp', 20000, 'chiếc', 'tốt'),
(4, 'Buffet buổi sáng', 15000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
(5, 'Buffet buổi trưa', 90000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
(6, 'Buffet buổi tối', 16000, 'suất', 'đầy đủ đồ ăn, tráng miệng');

insert into dich_vu (ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, so_nguoi_toi_da, tieu_chuan_phong, mo_ta_tien_nghi_khac, dien_tich_ho_boi, so_tang, ma_kieu_thue, ma_loai_dich_vu) VALUES
(1, 'Villa Beach Front', 25000, 10000000, 10, 'vip', 'Có hồ bơi', 500, 4, 3, 1),
(2, 'House Princess 01', 14000, 5000000, 7, 'vip', 'Có thêm bếp nướng', NULL, 3, 2, 2),
(3, 'Room Twin 01', 5000, 1000000, 8, 'normal', 'Có tivi', NULL, NULL, 4, 3),
(4, 'Villa No Beach Front', 22000, 9000000, 9, 'normal', 'Có hồ bơi', 300, 3, 3, 1),
(5, 'House Princess 02', 10000, 4000000, 5, 'normal', 'Có thêm bếp nướng', NULL, 2, 3, 2),
(6, 'Room Twin 02', 3000, 900000, 2, 'normal', 'Có tivi', NULL, NULL, 4, 3);

insert into hop_dong (ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, ma_nhan_vien, ma_khach_hang, ma_dich_vu) VALUES
(1, '2020-12-08', '2020-12-08', 0, 1, 1, 3),
(2, '2020-07-14', '2020-07-21', 20000, 3, 2, 1),
(3, '2021-03-15', '2021-03-17', 50000, 7, 3, 4),
(4, '2021-01-15', '2021-01-18', 100000, 2, 5, 2),
(5, '2021-07-03', '2021-07-15', 0, 3, 1, 3),
(6, '2021-06-01', '2021-06-03', 0, 7, 3, 2),
(7, '2021-03-10', '2021-03-12', 0, 5, 4, 5),
(8, '2021-04-12', '2021-04-14', 150000, 2, 6, 6),	
(9, '2021-04-12', '2021-04-14', 0, 3, 2, 5),
(10, '2021-05-10', '2021-05-12', 0, 5, 7, 1),
(11, '2021-04-21', '2021-04-26', 0, 2, 3, 2),
(12, '2021-05-25', '2021-05-27', 0, 7, 2, 3);

insert into hop_dong_chi_tiet (ma_hop_dong_chi_tiet, so_luong, ma_hop_dong, ma_dich_vu_di_kem) VALUES
(1, 5, 2, 4),
(2, 8, 2, 5),
(3, 15, 3, 6),
(4, 1, 3, 1),
(5, 3, 1, 3),
(6, 11, 3, 2),
(7, 1, 1, 1),
(8, 1, 1, 2),
(9, 2, 12, 2);

-- truy van sql 
-- 2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
select * from nhan_vien 
where (ho_ten like 'T%' or ho_ten like 'K%' or ho_ten like 'H%') 
and char_length(ho_ten) <= 15;

-- 3.	Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
select * 
from khach_hang 
where (timestampdiff(year, ngay_sinh, curdate()) between 18 and 50) 
and (dia_chi like '%đà nẵng%' or dia_chi like '%quảng trị%');


-- 4.	Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
select kh.ma_khach_hang, kh.ho_ten, count(hd.ma_hop_dong) as so_lan_dat_phong
from khach_hang kh
join loai_khach lk on kh.ma_loai_khach = lk.ma_loai_khach
join hop_dong hd on kh.ma_khach_hang = hd.ma_khach_hang
where lk.ten_loai_khach = 'Diamond'
group by kh.ma_khach_hang, kh.ho_ten
order by so_lan_dat_phong asc;


-- 5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien (Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet) cho tất cả các khách hàng đã từng đặt phòng. (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
select 
    kh.ma_khach_hang,
    kh.ho_ten,
    lk.ten_loai_khach,
    hd.ma_hop_dong,
    dv.ten_dich_vu,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    (dv.chi_phi_thue + ifnull(hdct.so_luong * dvdk.gia, 0)) as tong_tien
from khach_hang kh
left join loai_khach lk on kh.ma_loai_khach = lk.ma_loai_khach
left join hop_dong hd on kh.ma_khach_hang = hd.ma_khach_hang
left join dich_vu dv on hd.ma_dich_vu = dv.ma_dich_vu
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
left join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
order by kh.ma_khach_hang;


-- 6.	Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
select dv.ma_dich_vu, dv.ten_dich_vu, dv.dien_tich, dv.chi_phi_thue, ldv.ten_loai_dich_vu
from dich_vu dv
join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
where dv.ma_dich_vu not in (
    select distinct hd.ma_dich_vu
    from hop_dong hd
    where year(hd.ngay_lam_hop_dong) = 2021
    and month(hd.ngay_lam_hop_dong) in (1, 2, 3)
);


-- 7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
select dv.ma_dich_vu, dv.ten_dich_vu, dv.dien_tich, dv.so_nguoi_toi_da, dv.chi_phi_thue, ldv.ten_loai_dich_vu
from dich_vu dv
join loai_dich_vu ldv on dv.ma_loai_dich_vu = ldv.ma_loai_dich_vu
where dv.ma_dich_vu in (
    select distinct hd.ma_dich_vu
    from hop_dong hd
    where year(hd.ngay_lam_hop_dong) = 2020
)
and dv.ma_dich_vu not in (
    select distinct hd.ma_dich_vu
    from hop_dong hd
    where year(hd.ngay_lam_hop_dong) = 2021
);

-- 8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau. Học viên sử dụng theo 3 cách khác nhau để thực hiện yêu cầu trên.
-- cach 1 distinct 
select distinct ho_ten 
from khach_hang;

-- cach 2 group by
select ho_ten 
from khach_hang 
group by ho_ten;

-- cach 3 exists
select ho_ten 
from khach_hang kh1 
where not exists (
    select 1 from khach_hang kh2 
    where kh1.ho_ten = kh2.ho_ten 
    and kh1.ma_khach_hang > kh2.ma_khach_hang
);


-- 9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
select month(hd.ngay_lam_hop_dong) as thang, 
       count(distinct hd.ma_khach_hang) as so_luong_khach_hang
from hop_dong hd
where year(hd.ngay_lam_hop_dong) = 2021
group by month(hd.ngay_lam_hop_dong)
order by thang;

-- 10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong,ngay_ket_thuc,tien_dat_coc,so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
select hd.ma_hop_dong, 
       hd.ngay_lam_hop_dong, 
       hd.ngay_ket_thuc, 
       hd.tien_dat_coc, 
       coalesce(sum(hdct.so_luong), 0) as so_luong_dich_vu_di_kem
from hop_dong hd
left join hop_dong_chi_tiet hdct on hd.ma_hop_dong = hdct.ma_hop_dong
group by hd.ma_hop_dong, hd.ngay_lam_hop_dong, hd.ngay_ket_thuc, hd.tien_dat_coc
order by hd.ma_hop_dong;

-- 11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng có ten_loai_khach là “Diamond” và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.

-- 12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng),so_dien_thoai(kháchhàng),ten_dich_vu,so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem), tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.

-- 13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng. (Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau).

-- 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất. Thông tin hiển thị bao gồm ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung (được tính dựa trên việc count các ma_dich_vu_di_kem).

-- 15.	Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.

-- 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.

-- 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond, chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.

-- 18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).

-- 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.

-- 20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống, thông tin hiển thị bao gồm id (ma_nhan_vien, ma_khach_hang), ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.