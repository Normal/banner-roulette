# Users schema

# --- !Ups

CREATE TABLE CAMPAIGNS (
  ID    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  NAME  VARCHAR(255) NOT NULL,
  LINK  VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO CAMPAIGNS VALUES
  (NULL, 'Florida Panthers', 'http://panthers.nhl.com/'),
  (NULL, 'New York Rangers', 'http://rangers.nhl.com/'),
  (NULL, 'Toronto Maple Leafs', 'http://mapleleafs.nhl.com/');

# --- !Downs

DROP TABLE CAMPAIGNS;