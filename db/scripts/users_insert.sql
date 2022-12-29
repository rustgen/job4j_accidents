insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$xt0xv8961redy9XH0ak.TuwS1p6VQ.G.vEKpnkqUFRnWGYVK4No8u',
(select id from authorities where authority = 'ROLE_ADMIN'));