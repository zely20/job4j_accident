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

CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$TDVNzXvwj1z8y.kA33SDx.XzwMj5m4I/sBGtnjyXu6ACbn6IiIbUS',
        (select id from authorities where authority = 'ROLE_ADMIN'));