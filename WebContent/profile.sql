create table profile(
	pnum number primary key,
	id varchar2(15) references users(id),
	comments varchar2(100),
	orgfilename varchar2(50),
	savefilename varchar2(50),
	filesize number 
);
create sequence profile_seq;
commit;