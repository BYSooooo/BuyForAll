/* 테이블 생성 쿼리 */
CREATE TABLE Member(
memberId VARCHAR(20) PRIMARY KEY,
memberPwd VARCHAR(100),
memberName VARCHAR(20),
memberEmail VARCHAR(20),
memberGrade VARCHAR(20),
postNumber VARCHAR(7),
memberAddress VARCHAR(100),
lastLogin VARCHAR(20),
amountBuying INT,
joinDate VARCHAR(20)
);

