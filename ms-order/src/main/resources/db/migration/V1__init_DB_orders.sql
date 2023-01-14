create table orders
(
    id         bigserial PRIMARY KEY,
    user_id    bigint,
    cost       double precision,
    address    varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table orders_items
(
    id               bigserial primary key,
    order_id         bigint references orders (id),
    product_id       bigint,
    title            varchar(255),
    quantity         int,
    cost_per_product double precision,
    cost             double precision,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
);

create table carts
(
    id      UUID primary key,
    user_id bigint,
    cost    double precision
);

create table cart_items
(
    id               bigserial primary key,
    cart_id          UUID references carts (id),
    product_id       bigint,
    title            varchar(255),
    quantity         int,
    cost_per_product double precision,
    cost            double precision,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
);