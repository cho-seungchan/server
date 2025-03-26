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
    CREATED_DATE DATE default SYSDATE,
    UPDATED_DATE DATE default SYSDATE
);