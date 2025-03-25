
CREATE SEQUENCE SEQ_WISH;
CREATE TABLE TBL_WISH (
                          ID NUMBER CONSTRAINT PK_WISH PRIMARY KEY,
                          CREATED_DATE DATE DEFAULT SYSDATE,
                          UPDATED_DATE DATE DEFAULT SYSDATE,
                          MEMBER_ID NUMBER NOT NULL,
                          PLAN_ID NUMBER NOT NULL,
                          CONSTRAINT FK_WISH_MEMBER FOREIGN KEY(MEMBER_ID)
                          REFERENCES TBL_MEMBER(ID),
                          CONSTRAINT FK_WISH_PLAN FOREIGN KEY(PLAN_ID)
                          REFERENCES TBL_PLAN(ID)
);

DROP SEQUENCE SEQ_WISH;
DROP TABLE TBL_WISH;

INSERT INTO TBL_WISH (ID, MEMBER_ID, PLAN_ID)
VALUES (SEQ_WISH.NEXTVAL, 1, 3);