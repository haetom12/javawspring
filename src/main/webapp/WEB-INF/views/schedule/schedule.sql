show tables;

create table schedule2 (
	idx int   not null auto_increment primary key,
	mid varchar(20) not null,
	sDate datetime not null,		/* 일정 등록한 날짜 */
	part varchar(20) not null,  /* 1.모임, 2.업무, 3.학습, 4.여행, 0:기타 */
	content text not null
);

insert into schedule2 values (default, 'admin', '2023-01-09', '오락', '게임하기, 장소:집, 시간 15시');
insert into schedule2 values (default, 'admin', '2023-01-10', '여행', '해운대구경, 장소:부산, 시간 19시');
insert into schedule2 values (default, 'admin', '2023-01-10', '여행', '카페구경, 장소:부산, 시간 21시');
insert into schedule2 values (default, 'admin', '2023-01-11', '오락', '영화보기, 장소:영화관, 시간 12시');
insert into schedule2 values (default, 'admin', '2023-01-11', '학습', '코딩공부, 장소:그린컴퓨터, 시간 09시');
insert into schedule2 values (default, 'admin', '2023-01-13', '모임', '가족회의, 장소:집, 시간 19시');
insert into schedule2 values (default, 'admin', '2023-01-13', '기타', '집청소하기, 장소:집, 시간 09시');
insert into schedule2 values (default, 'admin', '2023-01-13', '기타', '놀기, 장소:어딘가, 시간 20시');
insert into schedule2 values (default, 'admin', '2023-02-10', '기타', '놀기, 장소:어딘가, 시간 20시');
insert into schedule2 values (default, 'admin', '2023-02-10', '기타', '놀기, 장소:어딘가, 시간 20시');
insert into schedule2 values (default, 'admin', '2023-02-10', '모임', '놀기, 장소:어딘가, 시간 20시');
insert into schedule2 values (default, 'admin', '2023-02-20', '기타', '놀기, 장소:어딘가, 시간 20시');

--select * from schedule2 where mid = 'admin' and sDate='2023-1' order by sDate;  (X)
--select * from schedule2 where mid = 'admin' and sDate='2023-01' order by sDate;  (X)
select * from schedule2 where mid = 'admin' and sDate='2023-01-13' order by sDate;
 -- 데이터베이스만 시작위치가 1부터임!!!!!!!!!
select * from schedule2 where mid = 'admin' and subString(sDate,1,7)='2023-01' order by sDate;
																																			 --%Y 년도 4자리
select * from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m')='2023-01' order by sDate;; 
select * from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m')='2023-01' group by subString(sDate,1,7) order by sDate;
select sDate,count(*) from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m')='2023-01' group by subString(sDate,1,7) order by sDate;
select sDate,part from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m')='2023-01' group by part order by sDate;
--select sDate,part from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m-%d')='2023-01-13' group by part order by sDate;
select sDate,part from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m')='2023-01' order by sDate, part;

select * from schedule2 where mid = 'admin' and date_format(sDate, '%Y-%m-%d') = '2023-01-09';




