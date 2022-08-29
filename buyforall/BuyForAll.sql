CREATE TABLE Member(
memberId VARCHAR(20) PRIMARY KEY,
memberPwd VARCHAR(100),
memberName VARCHAR(20),
memberEmail VARCHAR(40),
memberGrade VARCHAR(20),
postNumber VARCHAR(7),
memberAddress VARCHAR(100),
memberAddress2 VARCHAR(100),
lastLogin VARCHAR(20),
amountBuying INT,
joinDate VARCHAR(20)
);

CREATE TABLE Stuff (
barcode VARCHAR(10) PRIMARY KEY,
category_fst VARCHAR(20),
cartegory_se VARCHAR(20),
category_thd VARCHAR(20),
stuffName VARCHAR (50),
stuffPrice INT,
stuffExplain VARCHAR(100),
stuffStack INT
);

CREATE TABLE StuffFile(
fileNo INT AUTO_INCREMENT PRIMARY KEY,
barcode VARCHAR(10),
fileNameOrigin VARCHAR(100) NOT NULL,
fileNameSys VARCHAR(100) NOT NULL,
fileSize LONG NOT NULL
);