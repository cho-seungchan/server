CREATE SEQUENCE SEQ_PLAN;
create table TBL_PLAN
(
    ID NUMBER constraint PK_PLAN primary key,
    PLAN_NAME          VARCHAR2(1000) not null,
    PLAN_START_DATE    DATE default SYSDATE,
    PLAN_END_DATE      DATE default SYSDATE,
    PLAN_DEADLINE      DATE,
    PLAN_MAX_PERSONNEL NUMBER         not null,
    PLAN_MIN_PERSONNEL NUMBER         not null,
    PLAN_PRICE         NUMBER         not null,
    PLAN_START_ADDRESS VARCHAR2(2000) not null,
    PLAN_CONTENT       VARCHAR2(2000) not null,
    PLAN_FILE_PATH     VARCHAR2(2000),
    PLAN_FILE_SIZE     VARCHAR2(2000),
    PLAN_FILE_NAME     VARCHAR2(2000),
    MEMBER_ID NUMBER not null
        constraint FK_PLAN_MEMBER
            references TBL_MEMBER
                on delete cascade,
    COURSE_ID NUMBER not null
        constraint FK_PLAN_COURSE
            references TBL_COURSE
                on delete cascade,
    CREATED_DATE       DATE default SYSDATE,
    UPDATED_DATE       DATE default SYSDATE
);

SELECT * FROM TBL_PLAN
ORDER BY ID DESC;

SELECT * FROM TBL_WRITE_INCLUDE
ORDER BY ID DESC ;

SELECT * FROM TBL_SCHEDULE
ORDER BY ID DESC ;

SELECT ID, FEEDTYPE, FEEDCONTENT, CREATEDDATE, UPDATEDDATE, MEMBERID, MEMBERNICKNAME, MEMBERFILEPATH,
       MEMBERFILENAME, PLANID
FROM
    (SELECT ROWNUM R, ID, FEEDTYPE, FEEDCONTENT, CREATEDDATE, UPDATEDDATE, MEMBERID, MEMBERNICKNAME, MEMBERFILEPATH,
            MEMBERFILENAME, PLANID
     FROM
         (SELECT
              f.ID AS id,
              'REAL' AS feedType,
              f.FEED_CONTENT AS feedContent,
              TO_CHAR(f.CREATED_DATE, 'YYYY-MM-DD') AS createdDate,
              TO_CHAR(f.UPDATED_DATE, 'YYYY-MM-DD') AS updatedDate,
              m.ID AS memberId,
              m.MEMBER_NICKNAME AS memberNickname,
              m.MEMBER_FILE_PATH AS memberFilePath,
              m.MEMBER_FILE_NAME AS memberFileName,
              rf.PLAN_ID AS planId
          FROM
              TBL_FEED f
                  JOIN
              TBL_REAL_FEED rf ON f.ID = rf.ID
                  JOIN
              TBL_MEMBER m ON  rf.MEMBER_ID = m.ID
          WHERE PLAN_ID = 127
          ORDER BY ID DESC)
     WHERE ROWNUM <= ${pagination.endRow}
    )
WHERE R >= ${pagination.startRow}