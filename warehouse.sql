/*
Warehouse Manager
Table: Warehouse

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| name         | varchar |
| product_id   | int     |
| units        | int     |
+--------------+---------+
(name, product_id) is the primary key for this table.
Each row of this table contains the information of the products in each warehouse.


Table: Products

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| Width         | int     |
| Length        | int     |
| Height        | int     |
+---------------+---------+
product_id is the primary key for this table.
Each row of this table contains the information about the product dimensions (Width, Lenght and Height) in feets of each product.


Write an SQL query to report, How much cubic feet of volume does the inventory occupy in each warehouse.

warehouse_name
volume
Return the result table in any order.

*/
select w.name as warehouse_name,sum(p.length*p.width*p.height*w.units)as volume from warehouse w join
products p on w.product_id=p.product_id group by w.name;
