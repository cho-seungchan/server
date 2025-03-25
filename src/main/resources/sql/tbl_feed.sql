CREATE SEQUENCE SEQ_FEED;
CREATE TABLE TBL_FEED (
                          ID NUMBER CONSTRAINT PK_FEED PRIMARY KEY,
                          FEED_CONTENT VARCHAR2(2000) NOT NULL,
                          CREATED_DATE DATE DEFAULT SYSDATE,
                          UPDATED_DATE DATE DEFAULT SYSDATE
);
DELETE FROM TBL_FEED
WHERE ID = 20;

COMMENT ON TABLE TBL_FEED IS '피드 테이블';
COMMENT ON COLUMN TBL_FEED.ID IS '피드의 고유 ID';
COMMENT ON COLUMN TBL_FEED.FEED_CONTENT IS '피드 내용';
COMMENT ON COLUMN TBL_FEED.CREATED_DATE IS '생성 날짜';
COMMENT ON COLUMN TBL_FEED.UPDATED_DATE IS '수정 날짜';

SELECT * FROM TBL_FEED;
SELECT * FROM TBL_REAL_FEED;
SELECT * FROM TBL_GENERAL_FEED;
SELECT * FROM TBL_TOGETHER_FEED;
SELECT  * FROM TBL_MEMBER;
SELECT * FROM TBL_PLAN;

SELECT   f.ID,
         f.FEED_CONTENT,
         f.CREATED_DATE,
         f.UPDATED_DATE,
         r.MEMBER_ID,
         r.PLAN_ID
FROM     TBL_FEED f
             JOIN     TBL_REAL_FEED r
                      ON       f.ID = r.ID

WHERE    r.PLAN_ID = ${planId}

SELECT ID, FEEDTYPE, FEEDCONTENT, CREATEDDATE, UPDATEDDATE, MEMBERID, MEMBERNICKNAME, MEMBERFILEPATH,
       MEMBERFILENAME, PLANID, FILE_PATH, FILE_NAME
FROM
    (SELECT ROWNUM R, ID, FEEDTYPE, FEEDCONTENT, CREATEDDATE, UPDATEDDATE, MEMBERID, MEMBERNICKNAME, MEMBERFILEPATH,
            MEMBERFILENAME, PLANID, FILE_PATH, FILE_NAME
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
              rf.PLAN_ID AS planId,
              FF.FILE_PATH, FF.FILE_NAME
          FROM
              TBL_FEED f
                  JOIN
              TBL_REAL_FEED rf ON f.ID = rf.ID
                  JOIN
              TBL_MEMBER m ON  rf.MEMBER_ID = m.ID
                  JOIN
              TBL_REAL_FILE RFF ON F.ID = RFF.FEED_ID
                  JOIN TBL_FILE FF ON RFF.ID = FF.ID
          WHERE PLAN_ID = 127
          ORDER BY ID DESC)
     WHERE ROWNUM <= ${pagination.endRow}
    )
WHERE R >= ${pagination.startRow};
