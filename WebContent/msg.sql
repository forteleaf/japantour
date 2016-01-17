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