create table product(
productid int primary key not null,
productname nvarchar(50) not null,
productprice int,
productdate date,
remark nvarchar(50)
)
go

Insert Into Product(productid, productname, productprice, productdate, remark) Values
(1001, 'notebook',20,'2021-05-31','discount'),
(1002, 'Tesla',1600000,'2020-11-28','model3'),
(1003, 'iPhone',20000,'2021-05-29','12'),
(1004, 'mask',50,'2021-05-21','一包')

delete from Product where productid = 1005;
insert into Product (productid, productname, productprice, productdate, remark)
Values(1005, 'Airpods',8000,'2022-7-19','Good')

select * from product;