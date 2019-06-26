-- DROP sequence
--CASCADE CONSTRAINTS ;
DROP TABLE Base;
DROP TABLE Customer;
DROP TABLE Verification;
DROP TABLE Account;

-- Creating Base Table
CREATE TABLE Base (
    id NUMBER (6) NOT NULL,
    cId NUMBER (6) NOT NULL,
    vId NUMBER (6) NOT NULL, 
    acctNum NUMBER (6) NOT NULL, 
    PRIMARY KEY (id)
--    CONSTRAINT cId_fk FOREIGN KEY (cId) REFERENCES Customer(cId),
--    CONSTRAINT vId_fk FOREIGN KEY (vId) REFERENCES Verification(vId),
--    CONSTRAINT acNum_fk FOREIGN KEY (acctNum) REFERENCES Account(acctNum)
);


-- Creating Customer Table
CREATE TABLE Customer (
  		cId NUMBER(6) NOT NULL,
  		firstName VARCHAR(200),
  		lastName VARCHAR(200),
        PRIMARY KEY (cId)
  );
 
 
-- Creating Verification Table
CREATE TABLE Verification (
    vId NUMBER(6) NOT NULL,
    userName VARCHAR(200) NOT NULL,
    passWd VARCHAR(200) NOT NULL,
    PRIMARY KEY (vId)
    );

--    Creating Account Table
CREATE TABLE Account (
    acctNum NUMBER (6) NOT NULL,
    balance NUMBER (6),
    PRIMARY KEY (acctNum)
    );
    
/*
* Making Foreign Keys
*/
  
ALTER TABLE Base ADD CONSTRAINT FK_cId
    FOREIGN KEY (cId) REFERENCES Customer (cId);

ALTER TABLE Base ADD CONSTRAINT FK_vId
    FOREIGN KEY (vId) REFERENCES Verification (vId);

ALTER TABLE Base ADD CONSTRAINT FK_acNum
    FOREIGN KEY (acctNum) REFERENCES Account (acctNum);
    
INSERT INTO Customer VALUES (0, 'Jeff', 'Ibarra');
INSERT INTO Customer VALUES (1, 'Jose', 'Mendoza');

--UPDATE Customer SET firstName = 'Rival', lastName = 'HolyCity' WHERE cId = 4;

INSERT INTO verification VALUES (0,'jibarra','passWd');
INSERT INTO verification VALUES (1,'jMendoza','passIng');

INSERT INTO account VALUES (1001, 1000);
INSERT INTO account VALUES (1002, 1500);

INSERT INTO Base VALUES (0,0,0,1001);
INSERT INTO Base VALUES (1,1,1,1002);

SELECT * FROM Customer;
SELECT * FROM account;
SELECT * FROM verification;
SELECT * FROM base;

commit;