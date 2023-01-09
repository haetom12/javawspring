show tables;

create table member2 (
	idx int not null auto_increment, 	  	/* 회원 고유번호 */
	mid varchar(20) not null,        	  	/* 회원 아이디(중복불허) */
	pwd varchar(100) not null,       	  	/* 비밀번호(SHA암호화 처리) */
	nickName varchar(20) not null,  	  	/* 별명(중복불허/수정가능) */
	name 		 varchar(20) not null,      	/* 회원 성명 */
	gender 	 varchar(5) default '남자',  	/* 성별 */
	birthday datetime  default noW(),   	/* 생일 */
	tel		varchar(15),            		  	/* 전화번호(010-1234-5678) */
	address varchar(100),							  	/* 회원주소 (상품 배달시 기본주소로 사용) */
	email varchar(50) not null,				  	/* 이메일(아이디/비밀번호 분실시 사용) - 형식체크필수 */
	homePage varchar(50) not null,				/* 홈페이지(블로그) 주소 */
	job   varchar(20),										/* 회원 직업 */
	hobby varchar(20),										/* 회원 취미(2개이상은 '/'로 구분처리) */
	photo varchar(100) default 'noimage.jpg',	/* 회원 사진 */	
	content text,													/* 회원 자기 소개 */
	userInfor char(6)	default '공개',			/* 회원정보 공개여부(공개/비공개) */
	userDel  char(2)  default 'NO',   		/* 회원 탈퇴 신청 여부(OK:탈퇴신청한회원, NO:현재가입중인회원) */
	point int default 100, 								/* 회원누적포인트(가입시는 기본 100증정, 방문시마다 1회 10포인트증가, 최대 1일 50포인트까지) */
	level int default	4,									/* 회원등급(0:관리자, 1:운영자, 2:우수회원, 3:정회원, 4:준회원 */
	visitCnt int default 0, 							/* 방문횟수 */
	startDate datetime default now(),			/* 최초 가입일 */
	lastDate datetime default now(),      /* 마지막 접속일 */
	todayCnt int default 0,								/* 오늘 방문한 횟수 */
	primary key(idx,mid)           		  	/* 주키: idx(고유번호), mid(아이디) */
);


select * from member2;

desc member2;

--drop table member2;
--delete from member where idx=19;

insert into member2 values (default, 'admin', '1234', '관리맨','관리자', default, default, '010-5718-5014', '세종특별자치시', 'haetom@naver.com', 'https://www.naver.com', '학생', '볼링/게임', default, '관리자입니다.', default,default,default,0,default,default,default,default );
insert into member2 values (default, 'haetom', '1454', '해찬들','천해찬', default, default, '010-5718-5014', '세종특별자치시', 'haetom@naver.com', 'https://www.naver.com', '학생', '볼링/게임', default, '관리자입니다.', default,default,default,2,default,default,default,default );
insert into member2 values (default, 'haetom12', '1454', '해찬들','천해찬', default, default, '010-5718-5014', '세종특별자치시', 'haetom@naver.com', 'https://www.naver.com', '학생', '볼링/게임', default, '관리자입니다.', default,default,default,4,default,default,default,default );
