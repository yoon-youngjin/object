ROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER(
                         ID INT PRIMARY KEY AUTO_INCREMENT,
                         NAME VARCHAR(50) NOT NULL               COMMENT '이름'
);

DROP TABLE IF EXISTS DISCOUNT_CONDITION;
CREATE TABLE DISCOUNT_CONDITION(
                                   ID INT PRIMARY KEY AUTO_INCREMENT,
                                   POLICY_ID INT NOT NULL                  COMMENT '할인 정책 번호',
                                   CONDITION_TYPE VARCHAR(10) NOT NULL     COMMENT '할인 조건 타입',
                                   SEQUENCE INT NULL                       COMMENT '회차',
                                   DAY_OF_WEEK VARCHAR(10) NULL            COMMENT '요일',
                                   START_TIME TIME NULL                    COMMENT '시작 시간',
                                   END_TIME TIME NULL                      COMMENT '종료 시간'
);

DROP TABLE IF EXISTS DISCOUNT_POLICY;
CREATE TABLE DISCOUNT_POLICY(
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                POLICY_TYPE VARCHAR(10) NOT NULL        COMMENT '할인 정책 타입',
                                MOVIE_ID INT NOT NULL                   COMMENT '영화 ID',
                                AMOUNT INT NULL                         COMMENT '할인 금액',
                                PERCENT DOUBLE NULL                     COMMENT '할인 비율'
);

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE(
                      ID INT PRIMARY KEY AUTO_INCREMENT,
                      TITLE VARCHAR(100) NOT NULL             COMMENT '영화 제목',
                      FEE INT NOT NULL DEFAULT 0              COMMENT '영화 요금'
);


DROP TABLE IF EXISTS SCREENING;
CREATE TABLE SCREENING(
                          ID INT PRIMARY KEY AUTO_INCREMENT,
                          MOVIE_ID INT NOT NULL                   COMMENT '영화 ID',
                          SEQUENCE INT NOT NULL                   COMMENT '회차',
                          SCREENING_TIME TIMESTAMP                 COMMENT '시작시간'
);


DROP TABLE IF EXISTS RESERVATION;
CREATE TABLE RESERVATION(
                            ID INT PRIMARY KEY AUTO_INCREMENT,
                            CUSTOMER_ID INT NOT NULL                COMMENT '고객 번호',
                            SCREENING_ID INT NOT NULL               COMMENT '상영 번호',
                            AUDIENCE_COUNT INT NOT NULL             COMMENT '관람자 수',
                            FEE INT NOT NULL                        COMMENT '요금'
);