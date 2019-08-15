DROP TABLE IF EXISTS myuser;

CREATE TABLE myuser (
  user_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
  name VARCHAR(20) not null,
  email VARCHAR(20) not null,
  username VARCHAR(20) unique not null,
  password VARCHAR(60) not null,
  role VARCHAR(20) not null
);


create table persistent_logins (
  username varchar(64) not null, 
  series varchar(64) primary key, 
  token varchar(64) not null, 
  last_used timestamp not null
);