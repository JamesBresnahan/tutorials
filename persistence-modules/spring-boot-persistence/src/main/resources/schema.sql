drop table if exists USERS;
drop table if exists country;

CREATE TABLE person(
  id INTEGER not null,
  name varchar(100) not null,
  email varchar(180) not null,
  PRIMARY KEY ( id )
);

CREATE TABLE country (
      id   INTEGER      NOT NULL,
      name VARCHAR(128) NOT NULL,
      PRIMARY KEY (id)
    );