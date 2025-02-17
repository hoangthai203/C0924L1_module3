create database if not exists ss5_view_index_sp;
use ss5_view_index_sp;
create table products (
    id int auto_increment primary key,
    productcode varchar(50) not null,
    productname varchar(100) not null,
    productprice decimal(10,2) not null,
    productamount int not null,
    productdescription text,
    productstatus enum('còn hàng', 'hết hàng') not null
);

insert into products (productcode, productname, productprice, productamount, productdescription, productstatus) 
values
('p001', 'laptop acer nitro5', 22000.000, 10, 'laptop gamming hiệu suất cao', 'còn hàng'),
('p002', 'iphone 16 promax', 39999.00, 15, 'mẫu iphone mới nhất', 'còn hàng'),
('p003', 'tv samsung', 20500.000, 5, 'smart tv 4k', 'hết hàng'),
('p004', 'tai nghe sony', 5200.000, 20, 'tai nghe chống ồn', 'còn hàng');

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
create unique index idx_product_code on products(productcode);

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
create index idx_product_name_price on products(productname, productprice);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
explain select * from products where productname = 'laptop acer nitro5';

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
create view product_view as 
select productcode, productname, productprice, productstatus from products;

-- Tiến hành sửa đổi view

drop view if exists product_view;

create view product_view as 
select productcode, productname, productprice, productamount, productstatus from products;

select * from product_view;

-- Tiến hành xoá view
drop view product_view;

-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
delimiter //
create procedure getallproducts()
begin
    select * from products;
end //
delimiter ;

-- Tạo store procedure thêm một sản phẩm mới
delimiter //
create procedure addproduct(
    in p_productcode varchar(50),
    in p_productname varchar(100),
    in p_productprice decimal(10,3),
    in p_productamount int,
    in p_productdescription text,
    in p_productstatus enum('còn hàng', 'hết hàng')
)
begin
    insert into products (productcode, productname, productprice, productamount, productdescription, productstatus)
    values (p_productcode, p_productname, p_productprice, p_productamount, p_productdescription, p_productstatus);
end //
delimiter ;

call addproduct('p005', 'macbook pro 2025', 35000.000, 7, 'apple macbook pro 16-inch', 'còn hàng');

-- Tạo store procedure sửa thông tin sản phẩm theo id
delimiter //
create procedure updateproduct(
    in p_id int,
    in p_productname varchar(100),
    in p_productprice decimal(10,2),
    in p_productamount int,
    in p_productdescription text,
    in p_productstatus enum('còn hàng', 'hết hàng')
)
begin
    update products
    set productname = p_productname,
        productprice = p_productprice,
        productamount = p_productamount,
        productdescription = p_productdescription,
        productstatus = p_productstatus
    where id = p_id;
end //
delimiter ;

-- cập nhật sản phẩm
call updateproduct(1, 'laptop dell 2025', 23000.000, 8, 'mẫu dell mới nhất', 'còn hàng');

-- xóa sản phẩm theo id
delimiter //
create procedure deleteproduct(in p_id int)
begin
    delete from products where id = p_id;
end //
delimiter ;

-- Tạo store procedure xoá sản phẩm theo id
call deleteproduct(3);
