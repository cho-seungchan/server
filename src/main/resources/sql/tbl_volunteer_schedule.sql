CREATE SEQUENCE SEQ_VOLUNTEER_SCHEDULE;
CREATE TABLE TBL_VOLUNTEER_SCHEDULE(
                                       ID NUMBER CONSTRAINT PK_VOLUNTEER_SCHEDULE PRIMARY KEY,
                                       SCHEDULE_DAY DATE NOT NULL,
                                       SCHEDULE_CONTENT VARCHAR2(2000) NOT NULL,
                                       VOLUNTEER_ID NUMBER NOT NULL,
                                       CONSTRAINT FK_VOLUNTEER_SCHEDULE FOREIGN KEY(VOLUNTEER_ID)
                                           REFERENCES TBL_VOLUNTEER(ID) ON DELETE CASCADE
);