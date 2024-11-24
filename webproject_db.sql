--member테이블 작성
create table member(
    user_id varchar(50) PRIMARY KEY,
    user_name varchar(50) not null,
    password varchar(250) not null,
    nickname varchar(250) UNIQUE,
    email varchar(200) not null,
    phone_number varchar(200) not null,
    gender VARCHAR2(10) not null,
    address varchar2(50) not null,
    join_date date DEFAULT SYSDATE not null
);

--board테이블 작성
create table board(
    board_id number PRIMARY KEY,
    user_id VARCHAR2(250) not null,
    title VARCHAR2(100) DEFAULT
    '제목 없음',
    content VARCHAR2(2000) not null,
    postdate date DEFAULT sysdate not null,
    visitcount number DEFAULT 0,
    category VARCHAR2(50) not null
);

--file테이블 작성
CREATE TABLE files (
    file_id NUMBER PRIMARY KEY,       -- 고유 키 (숫자)
    board_id NUMBER NOT NULL,         -- board의 idx
    ofile VARCHAR2(255) NOT NULL,     -- 오리지널 파일 이름
    sfile VARCHAR2(255) NOT NULL,     -- 저장 파일 이름
    downcount NUMBER DEFAULT 0,       -- 다운로드 수 (기본값 0)
    upload_date DATE DEFAULT SYSDATE -- 업로드 날짜 (기본값 현재 날짜)
);


--comment 테이블
create table comments(
    cm_id number PRIMARY KEY,
    board_id number not null,
    user_id VARCHAR(50) not null,
    content varchar2(2000),
    cm_date DATE DEFAULT sysdate
);

--cm_id 시퀀스 작성
create sequence cm_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;


--file_id 시퀀스 작성
create sequence file_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;



--board_id 시퀀스 작성
create sequence board_seq
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;


--조인
select B.*, M.nickname, ofile, sfile, downcount
from board B
inner join member M
    on B.user_id=M.user_id
inner join files F
    on B.board_id=F.board_id
where B.board_id=317;
    
    
--외래키 지정
alter table files
add constraint fk_files_board
FOREIGN key (board_id)
REFERENCES board(board_id)
on DELETE cascade;

--commit
commit;

--member 삭제
drop table member;
drop table board;
drop table files;
drop table comments;

select * from member;
select * from board;

-- 삽입
insert into board(board_id, nickname, content, category)
values(board_seq.nextval,'test','오늘은 진짜 너무 힘들다 빨리 집에 가고싶다','list');