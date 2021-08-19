CREATE TABLE types (
                       id serial primary key,
                       name varchar(2000)
);

insert into types (name) values ('не выбрано');
insert into types (name) values ('Превышение скорости');
insert into types (name) values ('Обгон в неположенном месте');
insert into types (name) values ('Стоянка в запрещенном месте');
insert into types (name) values ('Нарушение разметки');

CREATE TABLE accident (
                          id serial primary key,
                          name varchar(2000),
                          type_id int not null REFERENCES types(id)
);

CREATE TABLE rules (
                       id serial primary key,
                       name varchar(2000)
);

insert into rules (name) values ('статья не выбрана');
insert into rules (name) values ('статья 18.13(скорость)');
insert into rules (name) values ('статья 18.13(обгон)');
insert into rules (name) values ('статья 18.22(стоянка)');
insert into rules (name) values ('статья 18.14(разметка)');

CREATE TABLE rules_accident (
                                accident_id integer references accident(id),
                                rules_id integer references rules(id),
                                PRIMARY KEY (accident_id, rules_id)
);

CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       PRIMARY KEY (username)
);

CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username)
);