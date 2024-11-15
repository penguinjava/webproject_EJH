CREATE TABLE member(
    id INT PRIMARY KEY NOT NULL,
    user_name VARCHAR2(50) NOT NULL,
    password VARCHAR2(100) NOT NULL,
    nickname VARCHAR2(50) NOT NULL UNIQUE,
    email VARCHAR(200) DEFAULT '등록된 정보 없음',
    phone_number VARCHAR2(200) DEFAULT '등록된 정보 없음',
    gender CHAR(1) NOT NULL,
    address VARCHAR2(50),
    CONSTRAINT gender_check CHECK (gender IN ('M','W'))
);

-- join_date컬럼 추가
alter table member
add join_date DATE DEFAULT SYSDATE;
-- members로 이름 변경
alter table member rename to members;
-- id를 varchar2(50)로 변경
alter table members
    modify (id VARCHAR2(50));
--기존 CONSTRAINT삭제
alter table members
drop constraint gender_check;
--다시 추가
alter table members
add check (gender in('M','W'));

/*board테이블 추가*/
create table board(
    idx number primary key,
    id varchar2(50) not null,
    title VARCHAR2(200) not null,
    content VARCHAR2(2000) not null,
    postdate date default sysdate not null,
    ofile varchar2(200),
    sfile varchar2(200),
    downcount number default 0,
    visitcount number default 0
);

--게시판의 외래키 생성
alter table board
    add constraint board_mem_fk2 foreign key (id)
    references members(id);
    
    
/* 댓글 테이블 생성*/    
create table commends(
    id number not null,
    no number primary key,
    content VARCHAR2(2000)
);

alter table commends
    MODIFY(id VARCHAR2(50));

--댓글의 외래키 생성
ALTER table commends
    add constraint board_mem_fk1 FOREIGN key (id)
    references members(id);

--테스트를 위해서 임시적으로 외래키 해제
alter table commends
drop constraint board_mem_fk1;

alter table board
drop constraint board_mem_fk2;

-- 이름 수전 --
alter table members
    rename column user_name to userName;

alter table members
    rename column join_date to joinDate;
    
alter table members
    rename column phone_number to phoneNumber;    
-----------------    
    
--시퀀스 추가--
create sequence idx_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
create sequence no_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;

select * from members;