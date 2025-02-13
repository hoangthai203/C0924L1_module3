create database if not exists quan_ly_ban_hang;
use quan_ly_ban_hang;

create table customers (
  customer_id int primary key auto_increment,
  customer_name varchar(100) not null,
  customer_age int
);

create table products (
  product_id int primary key auto_increment,
  product_name varchar(100) not null,
  product_price decimal(10,2) not null
);

create table orders (
  order_id int primary key auto_increment,
  customer_id int,
  order_date date not null,
  total_price int,
  foreign key (customer_id) references customers(customer_id)
);

create table order_details (
  order_details_id int auto_increment,
  order_id int,
  product_id int,
  order_quantity int not null,
  primary key (order_details_id),
  foreign key (order_id) references orders(order_id),
  foreign key (product_id) references products(product_id)
);

-- nhập dữ liệu --
insert into customers (customer_id, customer_name, customer_age) values
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);


insert into products (product_id, product_name, product_price) values
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);


insert into orders (order_id, customer_id, order_date, total_price) values
(1, 1, '2006-03-21', null),
(2, 2, '2006-03-23', null),
(3, 1, '2006-03-16', null);


insert into order_details (order_id, product_id, order_quantity) values
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 1),
(3, 1, 8),
(2, 5, 4),
(2, 3, 3);