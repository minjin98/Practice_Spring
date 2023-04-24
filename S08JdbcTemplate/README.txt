[S08JdbcTemplate]

1. DataSource
2. JdbcTemplate 쿼리
3. DB 관련 Exception
4. Transaction 처리

(MEMBER TABLE)
DROP TABLE MEMBER;
CREATE TABLE MEMBER
(
    ID NUMBER NOT NULL,
    NAME VARCHAR2(30) NOT NULL,
    EMAIL VARCHAR2(50), 
    PASSWORD VARCHAR2(100),
    REGDATE DATE DEFAULT SYSDATE,
    CONSTRAINT PK_MEMBER_ID PRIMARY KEY(ID)
);

(MEMBER_ID_SEQ SEQUENCE)
-- MEMBER .ID 시퀀스

DROP SEQUENCE MEMBER_ID_SEQ;
CREATE SEQUENCE MEMBER_ID_SEQ
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999
    NOCYCLE;
    
SELECT member_id_seq.nextval FROM dual; 
SELECT member_id_seq.currval FROM dual; 

(MySQL)
DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("HELLOUSER");
		ds.setPassword("HELLOUSER");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		
(Oracle)
DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.Oracle.Driver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("HELLOUSER");
		ds.setPassword("HELLOUSER");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);