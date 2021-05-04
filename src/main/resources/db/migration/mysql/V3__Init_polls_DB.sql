create table if not exists polls
(
    id          int auto_increment
        primary key,
    name        varchar(255) not null,
    description varchar(255) null,
    start_date  date         not null,
    end_date    date         null
);

create table if not exists questions
(
    id           int auto_increment
        primary key,
    pool_id      int          null,
    questionType int          null,
    name         varchar(255) not null,
    constraint FK2tmuvd8ipylotadrklpecujpv
        foreign key (pool_id) references polls (id)
);

create table if not exists answer_variants
(
    id          int auto_increment
        primary key,
    question_id int          null,
    text        varchar(255) not null,
    constraint FKlkv82bebomc0mvesrcq5qpd4a
        foreign key (question_id) references questions (id)
);
