insert into usr
(id, first_name, last_name, password, email, active)
values (1, 'admin', 'admin', 'admin', 'a@a.com', true);

insert into roles (id, name)
values (1, 'ADMIN'), (2, 'USER');

insert into user_roles values (1, 1);