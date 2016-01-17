create table users (
	id varchar2(15) primary key,
	pwd varchar2(15),
	name varchar2(10),
	birth date,
	email varchar2(30)
);

CREATE TABLE CATEGORY
(
LOCALNUM NUMBER,
LOCALNAME VARCHAR2(30),
PRIMARY KEY(LOCALNUM)
);

채팅방 만들기
create table chat(
	no number,
	id varchar2(10),
	msg varchar2(100),
	time date
)
create sequence chat_seq;

CREATE TABLE CATEGORY
(
LOCALNUM NUMBER,
LOCALNAME VARCHAR2(30),
PRIMARY KEY(LOCALNUM) 
);

insert into category values (1,'홋카이도');
insert into category values (2,'혼슈');
insert into category values (3,'시코쿠');
insert into category values (4,'큐슈');

create table japanintro
(
num number(5) primary key,
id varchar2(15) references users(id),
title varchar2(50),
content varchar2(1000),
time date,
loculnum references category(localnum)
);

insert into japanintro values (11,'hwang','hello japanintro','hello',sysdate,1);


create table comments (
	num number(5) primary key,
	id varchar2(10),
	comments varchar2(50)
);
create sequence comments_seq;
insert into comments values(comments_seq.nextval,'test','very good');
commit;

CREATE TABLE JAPANINFO(
 INUM NUMBER,
 ID VARCHAR2(15),
 TITLE VARCHAR2(50),
 CONTENT VARCHAR2(1000),
 TIME DATE,
 LOCALNUM NUMBER,
 LIKECNT NUMBER,
 ORGFILENAME VARCHAR2(50),
 SAVEFILENAME VARCHAR2(50),
 FILESIZE NUMBER,
 PRIMARY KEY(INUM),
 FOREIGN KEY(ID) REFERENCES USERS(ID) ON DELETE CASCADE,
 FOREIGN KEY(LOCALNUM) REFERENCES CATEGORY(LOCALNUM) ON DELETE CASCADE -- 일본정보
);
CREATE TABLE JAPANINFO_RE(
 REINUM NUMBER,
 INUM NUMBER,
 ID VARCHAR2(15),
 COMMENTS VARCHAR2(100),
 REGDATE DATE,
 REF NUMBER,
 LEV NUMBER,
 STEP NUMBER,
 PRIMARY KEY(REINUM),
 FOREIGN KEY(INUM) REFERENCES JAPANINFO(INUM) ON DELETE CASCADE,
 FOREIGN KEY(ID) REFERENCES USERS(ID) ON DELETE CASCADE -- 일본정보리플
 on delete cascade
);

insert into japaninfo values (1,'hwang','japaninfo','japaninfo',sysdate,1,10,'','','');

create table report(
rnum number primary key,
id varchar2(15), 
title varchar2(50),
content varchar2(1000),
time date,
localnum number,
likecnt number,
orgfilename varchar2(50),
savefilename varchar2(50),
filesize number,
foreign key(id) references users(id) on delete cascade,
foreign key(localnum) references category(localnum) on delete cascade
);
create sequence report_seq;

insert into report values (1,'hwang','report항목','report항목',sysdate,1,66,'','',0);

create table report_re
(
renum number primary key,
rnum number references report(rnum),
id varchar2(15) references users(id),
comments varchar2(100),
regdate date,
ref number,
lev number,
step number,
foreign key(rnum) references report(rnum) on delete cascade,
foreign key(id) references users(id) on delete cascade
);

/////////////////////// my page

create table profile(
pnum number primary key,
id varchar2(15) references users(id),
comments varchar2(100),
orgfilename varchar2(50),
savefilename varchar2(50),
filesize number 
);
create sequence profile_seq;

///////////////////////////////// 

create table japanintro
(
num number(5) primary key,
id varchar2(15) references users(id),
title varchar2(50),
content varchar2(1000),
time date,
localnum references category(localnum)
);
create sequence japanintro_seq;

create table msg(
	msgnum number(5) primary key,
	id varchar2(15) references users(id),
	toid varchar2(15),
	title varchar2(50),
	contents varchar2(1000),
	regdate date
);
create sequence msg_seq;
commit;