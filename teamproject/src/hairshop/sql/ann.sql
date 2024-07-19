create table member(
MNO		NUMBER constraint mno_PK primary key,
MID		NVARCHAR2(10) CONSTRAINT MID_NN_U NOT NULL UNIQUE,
MPW		NVARCHAR2(10) CONSTRAINT MPW_NN NOT NULL,
MNAME	NVARCHAR2(4) CONSTRAINT MNAME_NN NOT NULL,
MPHONE	NVARCHAR2(11) CONSTRAINT MPHONE_NN NOT NULL,
MGRADE NUMBER(1)
);

create table shop(
SNAME 	NVARCHAR2(10) constraint SNAME_NN NOT NULL,
SLOCATION NVARCHAR2(10) constraint SLOCATION_NN NOT NULL,
SNO		NUMBER		  constraint sno_NN not null,
sdesigner NVARCHAR2(10) constraint SDESIGNER_NN NOT NULL,
sopen		NVARCHAR2(10) constraint Sopen_NN NOT NULL,
sclose		NVARCHAR2(10) constraint Sclose_NN NOT NULL
);

create table cut(
csname 		NVARCHAR2(10) constraint csname_NN NOT NULL,
csno		number		constraint csno_nn not null,
CCode 		number 		constraint ccode_NN NOT NULL,
ccutname	nvarchar2(10) constraint ccutname_nn not null,
CPRICE 		NUMBER 	constraint CPRICE_NN NOT NULL,
CCONTENTS 	NVARCHAR2(2000) constraint cCONTENTS_NN NOT NULL
);


CREATE TABLE REVIEW(
RNO		NUMBER CONSTRAINT rno_PK primary key,
rdate	date DEFAULT SYSDATE constraint rdate_NN not null,
rwriter nvarchar2(20) constraint rwriter_NN not null,
rcontents nvarchar2(2000) constraint rcontents_NN not null,
rdesignernum number constraint rdn_nn not null
);


create table book(
bno 	number constraint bno_PK primary key,
bsname	nvarchar2(10) constraint bsname_nn not null,
bdate nvarchar2(100) constraint bdate_nn not null,
btime nvarchar2(100) constraint btime_nn not null,
bdesignernum number constraint bdn_nn not null,
bcut nvarchar2(10) constraint bcut_nn not null,
bname nvarchar2(10) constraint bid_nn not null,
busernum number constraint bun_nn not null
);

create table timeTable(
tno			number			constraint tno_nn not null,
ttime		nvarchar2(100)	constraint ttime_nn not null),
status 		nvarchar2(20)
;

select * from timeTable;


create sequence ccode_seq
increment by 1
start with 1
nocache;


select * from timetable;
select * from book;
select * from CUT;
select * from MEMBER;
select * from REVIEW;
select * from shop;



SELECT * FROM all_sequences where sequence_owner='ANN'; --모든 시퀀스 조회
