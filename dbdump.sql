alter table usr
    add constraint unq_email_usr unique (email);

create table usr
(
    id            bigserial   not null primary key,
    first_name    varchar(50) not null,
    second_name   varchar(50) not null,
    password      varchar(50) not null,
    email         varchar(50) not null,
    active        boolean     not null
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint pk_user_role
        primary key (user_id, role_id)
);

create table roles
(
    id   bigserial   not null primary key,
    name varchar(20) not null
);

create table products
(
    id          bigserial     not null primary key,
    description varchar(2048) not null,
    price       integer       not null,
    category_id bigint        not null,
    details_id  bigint,
    stock       int           not null,
    active      bool          not null,
    photo       varchar(2048),
    name        varchar(255)  not null
);

create table category
(
    id        bigserial   not null primary key,
    title     varchar(50) not null unique,
    parent_id bigint      null
);

create table order
(
    id           bigserial    not null primary key,
    user_id      bigint       not null,
    time_created timestamp    not null,
    payment_type varchar(50)  not null,
    address      varchar(255) not null,
    finalPrice   int          not null,
    orderStatus  varchar(50)  not null
);

create table cart
(
    id          bigserial not null primary key,
    user_id     bigint    not null,
    total_Price int       not null,
    count_item  int       not null
);

create table order_item
(
    id          bigserial not null primary key,
    order_id    bigint    not null,
    product_id  bigint    not null,
    quantity    bigint    not null,
    price       int       not null,
    total_price int       not null
);

create table cart_item
(
    id          bigserial not null primary key,
    order_id    bigint    not null,
    product_id  bigint    not null,
    quantity    bigint    not null,
    price       int       not null,
    total_price int       not null
);

create table user_contacts
(
    id      bigserial not null primary key,
    user_id bigint not null,
    phone   varchar(50),
    address varchar(255)
);



ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_id_USR FOREIGN KEY (user_id) REFERENCES usr (id) ON DELETE RESTRICT;
ALTER TABLE user_roles
    ADD CONSTRAINT fk_role_id_ROLES FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT;

ALTER TABLE products
    ADD CONSTRAINT fk_category_CATEGORY FOREIGN KEY (category_id) REFERENCES category ON DELETE RESTRICT;

ALTER TABLE cart_item
    ADD CONSTRAINT fk_order_id_ORDER FOREIGN KEY (order_id) REFERENCES cart ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE cart_item
    ADD CONSTRAINT fk_product_id_PRODUCTS FOREIGN KEY (product_id) REFERENCES products ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE order_item
    ADD CONSTRAINT fk_order_id_ORDER FOREIGN KEY (order_id) REFERENCES order_table ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE order_item
    ADD CONSTRAINT fk_product_id_PRODUCTS FOREIGN KEY (product_id) REFERENCES products ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE order_table
    ADD CONSTRAINT fk_user_id_USR FOREIGN KEY (user_id) REFERENCES usr ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE cart
    ADD CONSTRAINT fk_user_id_USR FOREIGN KEY (user_id) REFERENCES usr ON DELETE restrict ON UPDATE CASCADE;

ALTER TABLE user_contacts
    ADD CONSTRAINT fk_user_id_USR FOREIGN KEY (user_id) REFERENCES usr ON DELETE RESTRICT;


