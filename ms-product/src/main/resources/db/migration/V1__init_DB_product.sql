create table products
(
    id           bigserial PRIMARY KEY,
    title        varchar(255) not null,
    cost         double precision,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

insert into products(title, cost)
values ('aaa', 25.6),
       ('bbb', 110.25),
       ('ccc', 666.9),
       ('ddd', 1000.00),
       ('eee', 1.15),
       ('fff', 136.17),
       ('ggg', 951.6),
       ('hhh', 197.94),
       ('iii', 796.97),
       ('jjj', 18796.66),
       ('kkk', 222.22),
       ('lll', 2.68),
