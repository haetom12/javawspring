show tables;

create table webMessage (
	idx int not null auto_increment primary key,  /* 고유번호 */
	title varchar(100) not null, 				/* 메세지 제목 */
	content text not null,						/* 메세지 내용 */
	sendId  varchar(20) not null,			/* 보내는 사람 아이디 */
	sendSw  char(1) 		not null, 		/* 보낸메세지(s), (휴지통(g) - 생략), 휴지통에서 삭제(x)표시 */
	sendDate datetime default now(),		/* 메세지 보낸 날짜 */
	receiveId varchar(20) not null,		/* 받는 사람 아이디 */
	receiveSw char(1) not null,		/* 받은메세지(n), 읽은 메세지 (r), 휴지통(g), 휴지통에서 삭제(x) */
	receiveDate datetime default now()		/* 메세지 받은 날짜 */
);

desc webMessage;

-- 보냄
insert into webMessage values (default, '안녕 해톰아~~','주말에 시간있니?','admin','s',default,'haetom','n',default);
-- 읽음
update webMessage set receiveSw = 'r', receiveDate=now() where idx = 1;
-- 답장
insert into webMessage values (default, 'ㅎㅇ어드민','시간없음','haetom','s',default,'admin','n',default);
-- 읽음
update webMessage set receiveSw = 'r', receiveDate=now() where idx = 2;

update webMessage set receiveSw = 'g' where idx = 2;
update webMessage set sendSw = 'x' where idx = 1;
update webMessage set receiveSw = 'g' where idx = 1;
update webMessage set receiveSw = 'x' where idx = 1;

delete webMessage from webMessage where sendSw = 'x' and receiveSw = 'x';


