CREATE TABLE PBX (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, BUILDING VARCHAR(50), NAME VARCHAR(60), NOTE VARCHAR(150), OUTPUTCOUNT INTEGER, PRIMARY KEY (ID))
CREATE TABLE DATAOUTPUT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, DATADEVOUT INTEGER, MAC VARCHAR(20), NOTE VARCHAR(100), PHONENUMBER INTEGER, DEVICE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE HWPOSITION (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NAME VARCHAR(10), NOTE VARCHAR(100), PHONENUMBER CHAR(1), TECHNOLOGYTYPE CHAR(1), TELEXCHANGEOUTPUT INTEGER, TELECHANGE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE PBXOUTPUT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOTE VARCHAR(100), PBXOUT INTEGER, PHONENUMBER INTEGER, PBX_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE TELEXCHANGE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, BUILDING VARCHAR(50), NAME VARCHAR(60), NOTE VARCHAR(150), OUTPUTCOUNT INTEGER, PRIMARY KEY (ID))
CREATE TABLE CABLEHEAD (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, BUILDING VARCHAR(50), NAME VARCHAR(60), NOTE VARCHAR(150), OUTPUTCOUNT INTEGER, PRIMARY KEY (ID))
CREATE TABLE DATADEVICE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, BUILDING VARCHAR(50), NAME VARCHAR(60), NOTE VARCHAR(150), OUTPUTCOUNT INTEGER, PRIMARY KEY (ID))
CREATE TABLE CABHEADOUTPUT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, CABHEADOUT INTEGER, NOTE VARCHAR(100), PHONENUMBER INTEGER, CABLEHEAD_ID INTEGER, PRIMARY KEY (ID))
ALTER TABLE DATAOUTPUT ADD CONSTRAINT DATAOUTPUTDEVICEID FOREIGN KEY (DEVICE_ID) REFERENCES DATADEVICE (ID)
ALTER TABLE HWPOSITION ADD CONSTRAINT HWPSTIONTLCHANGEID FOREIGN KEY (TELECHANGE_ID) REFERENCES TELEXCHANGE (ID)
ALTER TABLE PBXOUTPUT ADD CONSTRAINT PBXOUTPUT_PBX_ID FOREIGN KEY (PBX_ID) REFERENCES PBX (ID)
ALTER TABLE CABHEADOUTPUT ADD CONSTRAINT CBHDOUTPUTCBLHADID FOREIGN KEY (CABLEHEAD_ID) REFERENCES CABLEHEAD (ID)
