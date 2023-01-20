show tables;


select * from user2;

create table user2 (
	mid varchar(5) not null,
	nickName varchar(20) not null,
	job varchar(10) not null
);

--drop table user2;

desc user;
desc user;
desc user2;

insert into user values (default, 'aaaaa', '에이맨', 29, '서울');
insert into user2 values ('bbbbbb', '에이다', '학생');

select * from user,user2;

