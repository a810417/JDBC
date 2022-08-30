use JDBCDemoDB;
select * from product;


create table profiles(
id int primary key not null identity(1,1),
[name] nvarchar(30) not null,
[address] nvarchar(30) not null,
phone varchar(50)
);

select * from profiles;

select * from profiles where address = ?;

delete from profiles where id = ?;

update profiles set phone = '66666' where [name] = '克拉克';


create table users(
id int primary key not null identity(1,1),
username varchar(50) not null,
pwd varchar(50) not null
);
insert into users (username, pwd)
values('abc', '666')

select * from users;
-- SQL Injection:" 'OR 1=1 -- "
select * from users where username = 'abc' and pwd = pwd;
select * from users where username = ''or 1=1 -- and pwd = pwd;


select * from product

create proc productProc(@proId int, @proName varchar(40) out)
as
begin
	select @proName = productname from product where productid = @proId
end


create table gallery(
id int primary key not null identity(1,1),
imageName varchar(50) not null,
image_file varbinary(max) not null
)

select * from gallery;

insert into gallery(imageName, image_file)
values(?, ?)