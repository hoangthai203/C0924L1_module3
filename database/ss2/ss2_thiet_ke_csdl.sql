create database if not exists quan_li_kho;
use quan_li_kho;

create table vattu(
mavattu varchar(20) primary key,
tenvattu varchar(50)
);

create table phieuxuat(
sophieuxuat int primary key auto_increment,
ngayxuat date
);

create table chitietphieuxuat(
phieuxuat_sophieuxuat int,
vattu_mavattu varchar(20),
dongiaxuat int,
soluongxuat int,
primary key(phieuxuat_sophieuxuat,vattu_mavattu),
foreign key (phieuxuat_sophieuxuat) references phieuxuat(sophieuxuat),
foreign key (vattu_mavattu) references vattu(mavattu)
);

create table phieunhap(
sophieunhap int primary key auto_increment,
ngaynhap date
);

create table chitietphieunhap(
phieunhap_sophieunhap int,
vattu_mavattu varchar(20),
dongianhap int,
soluongnhap int,
primary key(phieunhap_sophieunhap,vattu_mavattu),
foreign key (phieunhap_sophieunhap) references phieunhap(sophieunhap),
foreign key (vattu_mavattu) references vattu(mavattu)
);

create table dondathang(
sodathang int primary key auto_increment,
ngaydathang date
);

create table chitietdathang(
dondathang_sodathang int,
vattu_mavattu varchar(20),
primary key(dondathang_sodathang,vattu_mavattu),
foreign key (dondathang_sodathang) references dondathang(sodathang),
foreign key (vattu_mavattu) references vattu(mavattu)
);

create table nhacungcap(
manhacungcap varchar(20) primary key,
tennhacungcap varchar(50),
diachi varchar(50)
);

create table sdt(
sdtnhacungcap int primary key,
nhacungcap_manhacungcap varchar(20),
foreign key (nhacungcap_manhacungcap) references nhacungcap(manhacungcap)
);

create table cungcap(
dondathang_sodathang int,
nhacungcap_manhacungcap varchar(20),
primary key(dondathang_sodathang,nhacungcap_manhacungcap),
foreign key (dondathang_sodathang) references dondathang(sodathang),
foreign key (nhacungcap_manhacungcap) references nhacungcap(manhacungcap)
);
