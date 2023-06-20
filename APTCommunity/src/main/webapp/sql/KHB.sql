CREATE TABLE member (
  mem_num number NOT NULL,
  dongho varchar2(10) NOT NULL,
  auth number DEFAULT 1 NOT NULL ,
  CONSTRAINT PK_MEMBER PRIMARY KEY (mem_num)
);
create SEQUENCE member_seq;

CREATE TABLE member_detail (
  mem_num number NOT NULL,
  name varchar2(30) NOT NULL,
  passwd varchar2(30) NOT NULL,
  phone varchar2(15) NOT NULL,
  email varchar2(50) NOT NULL,
  carnum varchar2(15),
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  CONSTRAINT PK_MEMBER_DETAIL PRIMARY KEY (mem_num)
);

CREATE TABLE notice (
  no_num number NOT NULL,
  dept number NOT NULL,
  title varchar2(100) NOT NULL,
  content varchar2(2000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL ,
  modify_date date,
  filename varchar2(150),
  ip varchar2(40) NOT NULL,
  category_Status number NOT NULL,
  status number NOT NULL,
  CONSTRAINT PK_NOTICE PRIMARY KEY (no_num)
);
create SEQUENCE notice_seq;


CREATE TABLE board (
  board_num number NOT NULL,
  mem_num number NOT NULL,
  title varchar2(100) NOT NULL,
  content varchar2(2000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  filename varchar2(150),
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_BOARD PRIMARY KEY (board_num)
);
create SEQUENCE board_seq;

CREATE TABLE board_reply (
  re_num number NOT NULL,
  mem_num number NOT NULL,
  board_num number NOT NULL,
  content varchar2(1000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_BOARD_REPLY PRIMARY KEY (re_num)
);
create SEQUENCE board_reply_seq;

CREATE TABLE secondhand (
  se_num number NOT NULL,
  mem_num number NOT NULL,
  division number NOT NULL,
  title varchar2(100) NOT NULL,
  content varchar2(2000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  filename varchar2(150),
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_SECONDHAND PRIMARY KEY (se_num)
);
create SEQUENCE seconhand_seq;

CREATE TABLE secondhand_reply (
  re_num number NOT NULL,
  mem_num number NOT NULL,
  se_num number NOT NULL,
  content varchar2(1000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_SECONDHAND_REPLY PRIMARY KEY (re_num)
);
create SEQUENCE seconhand_reply_seq;

CREATE TABLE secondhand_fav (
  fav_num number NOT NULL,
  mem_num number NOT NULL,
  se_num number NOT NULL,
  CONSTRAINT PK_SECONDHAND_FAV PRIMARY KEY (fav_num)
);
create SEQUENCE seconhand_fav_seq;

CREATE TABLE inquiry (
  in_num number NOT NULL,
  mem_num number NOT NULL,
  title varchar2(100) NOT NULL,
  content varchar2(2000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  filename varchar2(150),
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_INQUIRY PRIMARY KEY (in_num)
);
create sequence inquiry_seq;

--자주묻는질문 테이블
CREATE TABLE question(
 que_num number not null,
 mem_num number not null,
 title varchar2(100) not null,
 content varchar2(2000) not null,
 reg_date date default SYSDATE not null,
 modify_date date,
 filename varchar2(150) 
 ip varchar2(40) not null
 CONSTRAINT PK_QUESTION PRIMARY KEY (que_num)
);
create SEQUENCE question_seq;

ALTER TABLE question ADD CONSTRAINT FK_member_TO_question_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

CREATE TABLE inquiry_manage (
  re_num number NOT NULL,
  in_num number NOT NULL,
  mem_num number NOT NULL,
  content varchar2(1000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_INQUIRY_MANAGE PRIMARY KEY (re_num)
);
create SEQUENCE inquiry_manage_seq;

CREATE TABLE fix (
  fix_num number NOT NULL,
  mem_num number NOT NULL,
  mem_auth number NOT NULL,
  title varchar2(100) NOT NULL,
  content varchar2(2000) NOT NULL,
  reg_date date DEFAULT SYSDATE NOT NULL,
  modify_date date,
  filename varchar2(150),
  ip varchar2(40) NOT NULL,
  CONSTRAINT PK_FIX PRIMARY KEY (fix_num)
);
create SEQUENCE fix_seq;

CREATE TABLE booking (
  bk_num number NOT NULL,
  room_num number NOT NULL,
  mem_num number NOT NULL,
  book_mem number NOT NULL,
  bk_date varchar2(5) NOT NULL,
  start_time varchar2(5) NOT NULL,
  end_time varchar2(5) NOT NULL,
  CONSTRAINT PK_BOOKING PRIMARY KEY (bk_num)
);
create SEQUENCE booking_seq;

CREATE TABLE room_info (
  room_num number NOT NULL,
  room_type varchar2(10) NOT NULL,
  room_name varchar2(100) NOT NULL,
  bk_status number NOT NULL,
  total_mem number NOT NULL,
  CONSTRAINT PK_ROOM_INFO PRIMARY KEY (room_num)
);
create SEQUENCE room_info_seq;

ALTER TABLE member_detail ADD CONSTRAINT FK_member_TO_member_detail_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

ALTER TABLE board ADD CONSTRAINT FK_member_TO_board_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

ALTER TABLE board_reply ADD CONSTRAINT FK_board_TO_board_reply_1 FOREIGN KEY (
  board_num
)
REFERENCES board (
  board_num
);

ALTER TABLE secondhand ADD CONSTRAINT FK_member_TO_secondhand_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

ALTER TABLE secondhand_reply ADD CONSTRAINT FK_secondhand_TO_secondhand_reply_1 FOREIGN KEY (
  se_num
)
REFERENCES secondhand (
  se_num
);

ALTER TABLE inquiry ADD CONSTRAINT FK_member_TO_inquiry_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

ALTER TABLE inquiry_manage ADD CONSTRAINT FK_inquiry_TO_inquiry_manage_1 FOREIGN KEY (
  in_num
)
REFERENCES inquiry (
  in_num
);

ALTER TABLE inquiry_manage ADD CONSTRAINT FK_member_TO_inquiry_manage_2 FOREIGN KEY (
)
REFERENCES member (
  mem_num
);

ALTER TABLE fix ADD CONSTRAINT FK_member_TO_fix_1 FOREIGN KEY (
  mem_num
)
REFERENCES member (
  mem_num
);

ALTER TABLE booking ADD CONSTRAINT FK_room_info_TO_booking_1 FOREIGN KEY (
  room_num
)
REFERENCES room_info (
  room_num
);