-- câu 1 --
select o.order_id as oid, o.order_date as odate, sum(od.order_quantity * p.product_price) as oprice 
from orders o
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by o.order_id, o.order_date;

-- câu 2 --
select c.customer_name, p.product_name, od.order_quantity from customers c
join orders o on c.customer_id = o.customer_id
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id;
-- câu 3 --
select c.customer_name from customers c
left join orders o on c.customer_id = o.customer_id
left join order_details od on o.order_id = od.order_id
where od.order_id is null;

-- câu 4 --
select o.order_id as oid, o.order_date as odate, sum(od.order_quantity * p.product_price) as oprice
from orders o
join order_details od on o.order_id = od.order_id
join products p on od.product_id = p.product_id
group by o.order_id, o.order_date;

