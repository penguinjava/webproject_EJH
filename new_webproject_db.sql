--member테이블 작성
create table member(
    user_id varchar(50) not null,
    user_name varchar(50) not null,
    password varchar(250) not null,
    nickname varchar(250) not null,
    email varchar(200) DEFAULT
    '등록된 이메일이 없습니다',
    phone_number varchar(200) DEFAULT
    '등록된 번호가 없습니다',
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
    '제목 없음' not null,
    content VARCHAR2(2000) not null,
    postdate date DEFAULT sysdate not null,
);