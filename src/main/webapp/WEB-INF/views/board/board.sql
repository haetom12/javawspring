show tables;

create table board2 (
	idx int not null auto_increment,  /* 게시글의 고유번호 */
	nickName varchar(20)  not null,		/* 게시글 올린사람의 닉네임 */
	title 	 varchar(100) not null,	  /* 게시글 글 제목 */
	email 	 varchar(50), 						/* 글쓴이의 메일주소(회원가입시에 필수 입력처리 되어 있다.) */
	homePage varchar(50),							/* 글쓴이의 홈페이지(블로그)주소 */
	content  text  not null,        	/* 글 내용 */
	wDate 	 datetime default now(),  /* 글 올린 날짜 */
	hostIp	 varchar(50) not null,		/* 글쓴이의 접속 IP */
	readNum	 int default 0,						/* 글 조회수 */
	good		 int default 0,						/* '좋아요' 클릭 횟수 누적하기 */
	mid			 varchar(20) not null,    /* 회원 아이디(내가 올린 게시글 전체 조회시때 사용) */
	primary key(idx)                  /* 댓글이나 계정당 좋아요는 테이블을 따로 뺴야한다  */
);

--drop table board22;

desc board2;
select * from board2;
select * from board2 order by idx desc;

create table boardReply2 (
	idx int not null auto_increment,  /* 댓글의 고유번호 */
	boardIdx int not null,						/* 원본글의 고유번호(외래키로 지정) */
	mid			 varchar(20) not null,			/* 댓글 올린이의 아이디 */
	nickName varchar(20) not null, 		/* 댓글 올린이의 닉네임 */
	wDate 	datetime default now(),		/* 댓글 올린 날짜 */
	hostIp varchar(50) not null,			/* 댓글올린 PC의 IP */
	content text not null,						/* 댓글 내용 */
	primary key(idx),
	foreign key(boardIdx) references board2(idx)
--	on update cascade
--	on delete restrict
);

desc boardReply2;
select * from boardReply2;
select * from boardReply2 order by idx desc;


insert into board2 values (default,'관리맨','게시판 서비스를 시작합니다.','haetom@hanmail.net','http://www.youtube.com', '이곳은 게시판입니다.',default, '192.168.50.23',default,default,'admin');
insert into board2 values (default,'해찬들','확인입니당2','haetom12@gmail.com','http://www.naver.com', '이곳은 게시판입니다.',default, '192.168.50.23',default,default,'haetom');
insert into board2 values (default,'관리맨','수정입니당','haetom@hanmail.net','http://www.youtube.com', '이곳은 게시판입니다.',default, '192.168.50.23',default,default,'admin');

--delete from user where mid='hahaha';

/* 날짜함수 처리 연습 */
select now(); 				-- now() : 오늘 날짜와 시간을 보여준다.
select year(now());    -- year()  : '년도' 출력
select month(now());   -- month() : '월' 출력
select day(now());     -- day() 	: '일' 출력
select date(now());		 -- date(now()) : 년-월-일 출력
select weekday(now()); -- 요일 : 0(월), 1(화), 2(수), 3(목), 4(금), 5(토), 6(일)
select dayofweek(now()); -- 요일 : 1(월), 2(화), 3(수), 4(수), 5(목), 6(금), 7(토)
select hour(now());     -- hour() : 시간
select minute(now());     -- minute() : 시간
select second(now());     -- second() : 시간

select year('2022-12-1');
select idx, year(wDate) from board2;
select idx, day(wDate) as 날짜 from board2;
select weekday(wDate) from board2;

/* 날짜 연산 */
-- date_add(date, interval 값 type)
select date_add(now(), interval 1 day);  --오늘 날짜보다 +1 출력
select date_add(now(), interval -1 day);  --오늘 날짜보다 -1 출력
select date_add(now(), interval 10 day_hour);  --오늘 날짜보다 +10 출력
select date_add(now(), interval -10 day_hour);  --오늘 날짜보다 -10 출력

-- date_sub(date, interval 값 type)
select date_sub(now(), interval 1 day);  --오늘 날짜보다 -1 출력
select date_sub(now(), interval -1 day);  --오늘 날짜보다 +1 출력

-- 년도:%y,%Y(4자리)  --월(숫자):%m, 월(영문):%M  --일:%d,  시(12시간):%h/시(24시간):%H  --분:%i  --초:%s
select idx, wDate from board2;
select idx, date_format(wDate, '%y-%M-%d') from board2;	--%M 월을 영문출력
select idx, date_format(wDate, '%y-%m-%d') from board2;  --%m 월을 영문출력
select idx, date_format(wDate, '%Y-%m-%d') from board2;
select idx, date_format(wDate, '%h-%i-%s') from board2;
select idx, date_format(wDate, '%H-%i-%s') from board2;

-- 현재부터 한달전의 날짜
select date_add(now(), interval -1 month);

-- 하루전 체크
select date_add(now(), interval -1 day);
select date_add(now(), interval -1 day), wDate from board2;

-- 날짜차이 계산 : DATEDIFF(시작날짜, 마지막날짜)
select datediff('2022-11-30' , '2022-12-01');
select datediff(now() , '2022-11-30');
select idx, wDate, datediff(now() , wDate) from board2;
select idx, wDate, datediff(now() , wDate) as day_diff from board2;
select *, datediff(now() , wDate) as day_diff from board2;

select timestampdiff(hour, '2022-11-30', now()) from board2;
select timestampdiff(hour, wDate, now()) from board2;

select *,datediff(now() , wDate) as day_diff, timestampdiff(hour, wDate, now()) as hour_diff from board2;

/* 이전글 다음글 체크 */
select * from board2 where idx < 6 order by idx desc limit 1;
select * from board2 where idx > 6 limit 1;
select * from board2 where idx < 6 order by idx desc limit 1;


/* 댓글의 수를 전체 List에 출력하기 연습 */
select * from boardReply2 order by idx desc;
-- 댓글테이블(boardReply2)에서 board2 테이블의 고유번호 19번글에 딸려있는 댓글의 개수는?
select count(*) from boardReply2 where boardIdx = 20;
-- 원본글의 고유번호와 함께 출력, 갯수의 별명은 replyCnt
select boardIdx, count(*) as replyCnt from boardReply2 where boardIdx = 19;

SELECT count(*) FROM boardReply2 WHERE boardIdx = 19 as replyCnt;

-- 댓글테이블(boardReply2)에서 board2 테이블의 고유번호 19번글에 딸려있는 댓글의 개수는?
-- 원본글의 고유번호와 함께 출력, 갯수의 별명은 replyCnt
-- 이때 원본글을 쓴 닉네임을 함께 출력하시오. 단, 닉네임은 board2(원본글)테이블에서 가져와서 출력하시오.
select boardIdx, nickName ,count(*) as replyCnt from boardReply2 where boardIdx = 19;
SELECT boardIdx,
		(SELECT nickName from board2 where idx = 19) AS nickName ,
		count(*) AS replyCnt 
		FROM boardReply2 WHERE boardIdx = 19;
		
-- 앞의 문장을 부모테이블(board2) 의 관점에서 보자...
SELECT mid, nickName FROM board2 where idx = 19;
-- 앞의 닉네임을 자식(댓글)테이블(boardReply2)에서 가져와서 보여준다면??
SELECT mid, 
	(select nickName from boardReply2 where boardIdx = 19)	as nickName 
	FROM board2 where idx = 19;

-- 부모관점(board2)에서 고유번호 35번의 아이디와, 현재글에 달려있는 댓글의 개수???
SELECT mid,
	(SELECT count(*) FROM boardReply2 WHERE boardIdx = 19) 
	FROM board2 WHERE idx =19;

-- 부모관점(board2)에서 board2테이블의 모든 내용와, 현재글에 달려있는 댓글의 개수를 가져오되, 최근글 5개만 출력?
SELECT *,
	(SELECT count(*) FROM boardReply2 WHERE boardIdx = board2.idx) as replyCnt
	FROM board2
	order by idx desc
	limit 5;
	
-- 부모관점(board2)에서 board2테이블의 모든 내용와, 현재글에 달려있는 댓글의 개수를 가져오되, 최근글 5개만 출력?
-- 각각의 테이블에 별명을 붙여서 앞의 내용을 변경시켜보자
SELECT *,
	(SELECT count(*) FROM boardReply2 WHERE boardIdx = b.idx) as replyCnt
	FROM board2 b
	order by idx desc
	limit 5;



