drop table IF EXISTS USERS;

create TABLE USERS (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(250) NOT NULL UNIQUE,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  gender VARCHAR(250) NOT NULL,
  picture VARCHAR(250) NOT NULL,
  created DATE NOT NULL,
  updated DATE NOT NULL
);