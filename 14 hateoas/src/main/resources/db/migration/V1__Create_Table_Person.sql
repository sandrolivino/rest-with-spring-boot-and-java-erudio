create table person (
id bigint generated by default as identity,
address varchar(60),
first_name varchar(80),
gender varchar(6),
last_name varchar(80),
primary key (id));