CREATE DATABASE LIC_POLICY_DATABASE;

SHOW DATABASES;

USE LIC_POLICY_DATABASE;

CREATE TABLE LIC_POLICY_TABLE(
POLICY_NUMBER int primary key auto_increment, 
POLICY_NAME varchar(30) NOT NULL, 
POLICY_HOLDER_NAME varchar(30) NOT NULL, 
POLICY_START_DATE date, 
PREMIUM_AMOUNT float, 
PREMIUM_TYPE varchar(15) NOT NULL
);

ALTER TABLE LIC_POLICY_TABLE
	auto_increment=501;
    
DROP TABLE LIC_POLICY_TABLE;

SELECT * FROM LIC_POLICY_TABLE;

INSERT INTO LIC_POLICY_TABLE (POLICY_NAME,POLICY_HOLDER_NAME,POLICY_START_DATE,PREMIUM_AMOUNT,PREMIUM_TYPE) VALUES
 ('Term Life Insurance', 'Kruthika', '2020-09-24', 4000, 'Quarterly');
 
 INSERT INTO LIC_POLICY_TABLE (POLICY_NAME,POLICY_HOLDER_NAME,POLICY_START_DATE,PREMIUM_AMOUNT,PREMIUM_TYPE) VALUES
 ('Universal Life Insurance', 'Soumya', '2020-09-22', 12000, 'Yearly');
 
 SELECT * FROM LIC_POLICY_TABLE WHERE POLICY_NUMBER=501;
 
 UPDATE LIC_POLICY_TABLE SET PREMIUM_AMOUNT=10000 WHERE POLICY_NUMBER=502;
 
 DELETE FROM LIC_POLICY_TABLE WHERE POLICY_NUMBER=502;