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

drop table users;

-- table photos
create table photos(
photoID int primary key identity(1,1)¡@not null,
imageURL varchar(255) not null,
userID int not null,
foreign key (userID) references users(userID),
createTime datetime default getdate()
)

drop table photos;

-- table likes
create table likes(
getLikeID int not null,
foreign key (getLikeID) references users(userID),
sendLikeID int not null,
foreign key (sendLikeID) references users(userID),
createTime datetime default getdate()
)

drop table likes;

select * from users;
select * from photos;
select * from likes;