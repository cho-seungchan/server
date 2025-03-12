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

SELECT ROWNUM R, M.MEMBER_NICKNAME, P.ID, P.PLAN_NAME, P.PLAN_START_DATE, P.PLAN_END_DATE, P.PLAN_DEADLINE,
       P.PLAN_MAX_PERSONNEL, P.PLAN_MIN_PERSONNEL, P.PLAN_PRICE, P.MEMBER_ID, P.COURSE_ID
FROM TBL_MEMBER M JOIN TBL_PLAN P
                       ON M.ID = P.MEMBER_ID
WHERE P.MEMBER_ID = 1  AND ROWNUM <= 5
ORDER BY ID DESC;

SELECT * FROM TBL_MEMBER;

SELECT ID, MEMBER_NICKNAME, PLAN_NAME, TO_CHAR(PLAN_START_DATE, 'MM/DD'), TO_CHAR(PLAN_END_DATE, 'MM/DD'), PLAN_DEADLINE, PLAN_MAX_PERSONNEL,
       PLAN_MIN_PERSONNEL, PLAN_PRICE, MEMBER_ID, COURSE_ID
       FROM
        (
        SELECT ROWNUM R , ID, MEMBER_NICKNAME, PLAN_NAME, PLAN_START_DATE, PLAN_END_DATE, PLAN_DEADLINE, PLAN_MAX_PERSONNEL,
               PLAN_MIN_PERSONNEL, PLAN_PRICE, MEMBER_ID, COURSE_ID
        FROM
            (
                SELECT M.MEMBER_NICKNAME, P.ID, P.PLAN_NAME, P.PLAN_START_DATE, P.PLAN_END_DATE, P.PLAN_DEADLINE,
                       P.PLAN_MAX_PERSONNEL, P.PLAN_MIN_PERSONNEL, P.PLAN_PRICE, P.MEMBER_ID, P.COURSE_ID
                FROM TBL_MEMBER M JOIN TBL_PLAN P
                                       ON M.ID = P.MEMBER_ID
                WHERE MEMBER_ID = 1
                ORDER BY P.ID DESC
            )
            WHERE ROWNUM <= ${pagination.endRow}
            )
WHERE R >= ${pagination.startRow};

SELECT TO_CHAR(SYSDATE, 'MM-DD') FROM DUAL;

SELECT * FROM TBL_SCHEDULE
ORDER BY ID DESC ;

SELECT * FROM TBL_WRITE_INCLUDE
ORDER BY ID DESC ;


