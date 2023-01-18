show tables;

create table qrCode(
	idx char(8) not null,
	qrCode varchar(200) not null,
	name varchar(100) not null,
	price int not null,
	ingredient varchar(200) not null
);

select * from qrCode;

drop table qrCode;

desc qrCode;
