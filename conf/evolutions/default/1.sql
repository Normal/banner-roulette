# Users schema

# --- !Ups

CREATE TABLE CAMPAIGNS (
  ID    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  NAME  VARCHAR(255) NOT NULL,
  LINK  VARCHAR(255) NOT NULL,
  IMAGE BLOB NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO CAMPAIGNS VALUES
  (NULL, 'name1', 'http://campaign1.ru', 'image1'),
  (NULL, 'name2', 'http://campaign2.ru', 'image2'),
  (NULL, 'name3', 'http://campaign3.ru', 'image3');

# --- !Downs

DROP TABLE CAMPAIGNS;