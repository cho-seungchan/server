
CREATE SEQUENCE SEQ_WISH;
CREATE TABLE TBL_WISH (
                          ID NUMBER CONSTRAINT PK_WISH PRIMARY KEY,
                          CREATE_DATE DATE DEFAULT SYSDATE,
                          UPDATE_DATE DATE DEFAULT SYSDATE,
                          MEMBER_ID NUMBER NOT NULL,
                          COURSE_ID NUMBER NOT NULL,
                          CONSTRAINT FK_WISH_MEMBER FOREIGN KEY(MEMBER_ID)
                          REFERENCES TBL_MEMBER(ID),
                          CONSTRAINT FK_WISH_COURSE FOREIGN KEY(COURSE_ID)
                          REFERENCES TBL_COURSE(ID)
);