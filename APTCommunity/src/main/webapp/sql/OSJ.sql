
-- DDL for Table MEMBER

CREATE TABLE MEMBER (
  MEM_NUM NUMBER NOT NULL,
  DONGHO VARCHAR2(10) NOT NULL,
  AUTH NUMBER DEFAULT 1 NOT NULL,
  CONSTRAINT PK_MEMBER PRIMARY KEY (MEM_NUM)
);

-- DDL for Table MEMBER_DETAIL

CREATE TABLE MEMBER_DETAIL (
  MEM_NUM NUMBER NOT NULL,
  NAME VARCHAR2(30) NOT NULL,
  PASSWD VARCHAR2(30) NOT NULL,
  PHONE VARCHAR2(15) NOT NULL,
  EMAIL VARCHAR2(50) NOT NULL,
  CARNUM VARCHAR2(15),
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  CONSTRAINT PK_MEMBER_DETAIL PRIMARY KEY (MEM_NUM),
  CONSTRAINT FK_MEMBER_TO_MEMBER_DETAIL_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table BOARD

CREATE TABLE BOARD (
  BOARD_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  FILENAME VARCHAR2(150),
  IP VARCHAR2(40) NOT NULL,
  CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM),
  CONSTRAINT FK_MEMBER_TO_BOARD_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table BOARD_REPLY

CREATE TABLE BOARD_REPLY (
  RE_NUM NUMBER NOT NULL,
  BOARD_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  CONTENT VARCHAR2(1000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  CONSTRAINT PK_BOARD_REPLY PRIMARY KEY (RE_NUM),
  CONSTRAINT FK_BOARD_TO_BOARD_REPLY_1 FOREIGN KEY (BOARD_NUM) REFERENCES BOARD (BOARD_NUM),
  CONSTRAINT FK_MEMBER_TO_BOARD_REPLY_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table ROOM_INFO

CREATE TABLE ROOM_INFO (
  ROOM_NUM NUMBER NOT NULL,
  ROOM_TYPE VARCHAR2(30) NOT NULL,
  ROOM_NAME VARCHAR2(30) NOT NULL,
  ROOM_STATUS NUMBER DEFAULT 0 NOT NULL,
  TOTAL_MEM NUMBER NOT NULL,
  FILENAME VARCHAR2(150),
  CONSTRAINT PK_ROOM_INFO PRIMARY KEY (ROOM_NUM)
);

-- DDL for Table BOOKING

CREATE TABLE BOOKING (
  BK_NUM NUMBER NOT NULL,
  ROOM_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  BOOK_MEM NUMBER NOT NULL,
  BK_STATUS NUMBER NOT NULL,
  BK_DATE VARCHAR2(10) ,
  START_TIME VARCHAR2(10),
  END_TIME VARCHAR2(10),
  CONSTRAINT PK_BOOKING PRIMARY KEY (BK_NUM),
  CONSTRAINT FK_ROOM_INFO_TO_BOOKING_1 FOREIGN KEY (ROOM_NUM) REFERENCES ROOM_INFO (ROOM_NUM),
  CONSTRAINT FK_MEMBER_TO_BOOKING_2 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table FIX

CREATE TABLE FIX (
  FIX_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  MEM_AUTH NUMBER NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  FILENAME VARCHAR2(150),
  CONSTRAINT PK_FIX PRIMARY KEY (FIX_NUM),
  CONSTRAINT FK_MEMBER_TO_FIX_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table FIX_REPLY

CREATE TABLE FIX_REPLY (
  RE_NUM NUMBER NOT NULL,
  FIX_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  CONTENT VARCHAR2(1000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  CONSTRAINT PK_FIX_REPLY PRIMARY KEY (RE_NUM),
  CONSTRAINT FK_MEMBER_TO_FIX_REPLY_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE,
  CONSTRAINT FK_FIX_TO_FIX_REPLY_2 FOREIGN KEY (FIX_NUM) REFERENCES FIX (FIX_NUM)
);

-- DDL for Table INQUIRY

CREATE TABLE INQUIRY (
  IN_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  TITLE VARCHAR2(200) NOT NULL,
  CONTENT VARCHAR2(1000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  FILENAME VARCHAR2(150),
  CONSTRAINT PK_INQUIRY PRIMARY KEY (IN_NUM),
  CONSTRAINT FK_MEMBER_TO_INQUIRY_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table INQUIRY_MANAGE

CREATE TABLE INQUIRY_MANAGE (
  RE_NUM NUMBER NOT NULL,
  IN_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  CONSTRAINT PK_INQUIRY_MANAGE PRIMARY KEY (RE_NUM),
  CONSTRAINT FK_INQUIRY_TO_INQUIRY_MANAGE_1 FOREIGN KEY (IN_NUM) REFERENCES INQUIRY (IN_NUM),
  CONSTRAINT FK_MEMBER_TO_INQUIRY_MANAGE_2 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table NOTICE

CREATE TABLE NOTICE (
  NO_NUM NUMBER NOT NULL,
  DEPT NUMBER NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  FILENAME VARCHAR2(150),
  IP VARCHAR2(40) NOT NULL,
  CATEGORY_STATUS NUMBER NOT NULL,
  STATUS NUMBER NOT NULL,
  CONSTRAINT PK_NOTICE PRIMARY KEY (NO_NUM)
);

-- DDL for Table QUESTION

CREATE TABLE QUESTION (
  QUE_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  TITLE VARCHAR2(200) NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  FILENAME VARCHAR2(150),
  CONSTRAINT PK_QUESTION PRIMARY KEY (QUE_NUM),
  CONSTRAINT FK_MEMBER_TO_QUESTION_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);



-- DDL for Table SECONDHAND

CREATE TABLE SECONDHAND (
  SE_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  DIVISION NUMBER NOT NULL,
  TITLE VARCHAR2(100) NOT NULL,
  CONTENT VARCHAR2(2000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  FILENAME VARCHAR2(150),
  CONSTRAINT PK_SECONDHAND PRIMARY KEY (SE_NUM),
  CONSTRAINT FK_MEMBER_TO_SECONDHAND_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table SECONDHAND_FAV

CREATE TABLE SECONDHAND_FAV (
  FAV_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  SE_NUM NUMBER NOT NULL,
  CONSTRAINT PK_SECONDHAND_FAV PRIMARY KEY (FAV_NUM),
  CONSTRAINT FK_MEMBER_TO_SECONDHAND_FAV_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

-- DDL for Table SECONDHAND_REPLY

CREATE TABLE SECONDHAND_REPLY (
  RE_NUM NUMBER NOT NULL,
  MEM_NUM NUMBER NOT NULL,
  SE_NUM NUMBER NOT NULL,
  CONTENT VARCHAR2(1000) NOT NULL,
  REG_DATE DATE DEFAULT SYSDATE NOT NULL,
  MODIFY_DATE DATE,
  IP VARCHAR2(40) NOT NULL,
  CONSTRAINT PK_SECONDHAND_REPLY PRIMARY KEY (RE_NUM),
  CONSTRAINT FK_SECONDHAND_TO_SECONDHAND_REPLY_1 FOREIGN KEY (SE_NUM) REFERENCES SECONDHAND (SE_NUM),
  CONSTRAINT FK_SECONDHAND_REPLY_MEMBER_NUM_1 FOREIGN KEY (MEM_NUM) REFERENCES MEMBER (MEM_NUM) ON DELETE CASCADE
);

create SEQUENCE room_info_seq;
create SEQUENCE booking_seq;
create SEQUENCE fix_reply_seq;
create SEQUENCE fix_seq;
create SEQUENCE inquiry_manage_seq;
create SEQUENCE inquiry_seq;
create SEQUENCE secondhand_fav_seq;
create SEQUENCE secondhand_reply_seq;
create SEQUENCE secondhand_seq;
create SEQUENCE board_reply_seq;
create SEQUENCE board_seq;
create SEQUENCE notice_seq;
create SEQUENCE member_seq;
