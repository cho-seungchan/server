CREATE SEQUENCE SEQ_REAL_STAR;
CREATE TABLE TBL_REAL_STAR(
	ID NUMBER CONSTRAINT PK_REAL_STAR PRIMARY KEY,
	REAL_STAR NUMBER(1) DEFAULT 0,
	FEED_ID NUMBER NOT NULL,
	CONSTRAINT FK_REAL_SART_REAL_FEED FOREIGN KEY(FEED_ID)
	REFERENCES TBL_REAL_FEED(ID) ON DELETE CASCADE
);
COMMENT ON TABLE TBL_REAL_STAR IS '사용자 평가(별점) 관련 정보를 저장하는 테이블';
COMMENT ON COLUMN TBL_REAL_STAR.ID IS 'TBL_REAL_STAR의 기본 키, 고유 식별자';
COMMENT ON COLUMN TBL_REAL_STAR.REAL_STAR IS '평점(별점), 0~5의 값. 기본값은 0';
COMMENT ON COLUMN TBL_REAL_STAR.FEED_ID IS 'TBL_REAL_FEED 테이블의 ID에 대한 외래키';