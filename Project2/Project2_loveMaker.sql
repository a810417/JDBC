create database Project2_LoverMaker;

use Project2_LoverMaker;

-- table users
create table users(
userID¡@int primary key identity(1,1),
userName nvarchar(255) not null,
userPassword varchar(50) not null,
userGender varchar(50) not null,
userHeight int not null,
userWeight int not null,
createTime datetime default getdate()
)

-- table photos
create table photos(
photoID int primary key identity(1,1)¡@not null,
--imageURL varchar(255) not null,
imageFile varbinary(max) not null,
userID int not null,
foreign key (userID) references users(userID),
createTime datetime default getdate()
)

-- table likes
create table likes(
getLikeID int not null,
foreign key (getLikeID) references users(userID),
sendLikeID int not null,
foreign key (sendLikeID) references users(userID),
createTime datetime default getdate()
)

-- ·j´Mtable
select * from users;
select * from photos;
select * from likes;

truncate table likes;
truncate table photos;
truncate table users;

-- §R°£table
drop table likes;
drop table photos;
drop table users;

UPDATE users SET userPassword='man001', userGender='female', userHeight=160, userWeight=45 WHERE userName='man001';

delete from likes where userID=;
delete from photos where userID=2;
delete from users where userID=4;

INSERT INTO users (userName, userPassword, userGender, userHeight, userWeight) VALUES ('Alan', 'aaa000', 'Male', 180, 70);