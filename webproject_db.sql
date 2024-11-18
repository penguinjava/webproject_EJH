--member테이블 작성
create table member(
    user_id varchar(50) not null,
    user_name varchar(50) not null,
    password varchar(250) not null,
    nickname varchar(250) not null,
    email varchar(200) not null,
    phone_number varchar(200) not null,
    gender VARCHAR2(10) not null,
    address varchar2(50) not null,
    join_date date DEFAULT SYSDATE not null,
    PRIMARY KEY (user_id),
    UNIQUE(nickname)
);

--board테이블 작성
create table board(
    board_id number not null,
    user_id VARCHAR2(50) not null,
    title VARCHAR2(100) DEFAULT
    '제목 없음',
    content VARCHAR2(2000) not null,
    postdate date DEFAULT sysdate not null,
    visitcount number DEFAULT 0,
    category VARCHAR2(50) not null
);

--board_id 시퀀스 작성
create sequence board_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;

--commit
commit;

--member 삭제
drop table members;
drop table board;
drop table commends;

select * from member;