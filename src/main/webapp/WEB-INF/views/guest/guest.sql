show tables;

create table guest2(
	idx int not null auto_increment primary key,     /* 고유번호 */
	name varchar(20) not null,   	 /* 방문자 성명 */
	email varchar(60),          	 /* 이메일 주소 */
	homePage varchar(60),       	 /* 홈페이지 주소 */
	visitDate datetime default now(),	 /* 방문일자 / EL 표기법에ㅔ서 첫글자 다음이 대문자면 첫글자도 대문자로 쓴다!!!   */
	hostIp varchar(50) not null,   /* 방문자 IP */
	content text not null          /* 방문소감 */
);

desc guest2;

insert into guest2 values (default,'관리자','haetom12@gmail.com','haetom@naver.com',default,'192.168.50.23','방명록 서비스를 개시합니다');

select * from guest2;



--drop table guest;