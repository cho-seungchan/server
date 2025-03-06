CREATE SEQUENCE SEQ_FILE;
CREATE TABLE TBL_FILE (
    ID NUMBER CONSTRAINT PK_FILE PRIMARY KEY,
    FILE_PATH VARCHAR2(1000) NOT NULL,
    FILE_NAME VARCHAR2(1000) NOT NULL,
    FILE_SIZE VARCHAR2(200) DEFAULT '',
    CREATED_DATE DATE DEFAULT SYSDATE,
    UPDATED_DATE DATE DEFAULT SYSDATE
);

COMMENT ON TABLE TBL_FILE IS '파일 테이블';
COMMENT ON COLUMN TBL_FILE.ID IS '파일의 고유 ID';
COMMENT ON COLUMN TBL_FILE.FILE_PATH IS '파일 경로';
COMMENT ON COLUMN TBL_FILE.FILE_NAME IS '파일 이름';
COMMENT ON COLUMN TBL_FILE.FILE_SIZE IS '파일 크기';
COMMENT ON COLUMN TBL_FILE.CREATED_DATE IS '생성 날짜';
COMMENT ON COLUMN TBL_FILE.UPDATED_DATE IS '수정 날짜';