create table if not exists users
(
    username varchar(255) not null
        primary key,
    password varchar(255) not null,
    enabled  bit          not null
);


create table if not exists authorities
(
    username  varchar(255) not null
        primary key,
    authority varchar(255) not null
);

create table if not exists users_authorities
(
    user_username      varchar(255) not null,
    authority_username varchar(255) not null,
    constraint FKirolm9syye5giedkp35a0g70p
        foreign key (user_username) references users (username),
    constraint FKprk0cs3lqyjoea305ddv1tdyu
        foreign key (authority_username) references authorities (username)
);
