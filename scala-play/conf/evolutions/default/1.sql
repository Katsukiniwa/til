-- Users schema

-- !Ups

CREATE TABLE Employee (
    id bigserial PRIMARY KEY,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    fullName varchar(255) NOT NULL,
    isAdmin boolean NOT NULL
);
--insert into employee (email, password, fullName, isAdmin) values ('test001@example.com', 'raw_password', 'Tom Brown', true);

-- !Downs

DROP TABLE Employee;
