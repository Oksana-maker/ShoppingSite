insert into users(username, password)
values ('admin', '$2a$10$qVR8oMwD/FKCIG2R4eHaCeFSw.RLiDut1/Zs1/GZAEXffLBy/4oZW'),
       ('user', '$2a$10$YJCTX2lLSSbZncJzF7P9DeoQjiE2Ubkq9V7HoRpDaPZcTD20kha9q');

insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('user', 'ROLE_USER');
