CREATE TABLE types (
                    id serial primary key,
                    name varchar(2000)
);

CREATE TABLE accident (
                          id serial primary key,
                          name varchar(2000),
                          type_id int not null REFERENCES types(id)
);

CREATE TABLE rules (
                       id serial primary key,
                       name varchar(2000)
);

CREATE TABLE rules_accident (
                                accident_id integer references accident(id),
                                rules_id integer references rules(id),
                                PRIMARY KEY (accident_id, rules_id)
);