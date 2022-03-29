create table roles_permission
(
    id      varchar(255) not null
        primary key,
    name    varchar(255) null,
    role_id bigint       null,
    constraint FKicrko0md6clwqmll1bchnl02j
        foreign key (role_id) references role_table (id)
);

INSERT INTO roles_permission (id, name, role_id) VALUES ('1', '1', 1);
