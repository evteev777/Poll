create table if not exists persons
(
    id       int auto_increment
        primary key,
    email varchar(255) null,
    constraint persons_users_username_fk
        foreign key (email) references users (username)
);

