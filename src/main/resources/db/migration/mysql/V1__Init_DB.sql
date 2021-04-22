create table answer
(
    id          int auto_increment
        primary key,
    user_id     int           not null,
    poll_id     int           not null,
    question_id int           not null,
    answer      varchar(1000) null
)
    engine = MyISAM;

create index FK7mluqeoggakn10mca9veouh1p
    on answer (question_id);

create index FKov29nqfe0683bp4m3c3teqmom
    on answer (poll_id);

create index FKsdj8jab9t00diflkysw22k7bv
    on answer (user_id);

create table answer_variants
(
    id   int auto_increment
        primary key,
    text varchar(255) null
)
    engine = MyISAM;

create table polls
(
    id          int auto_increment
        primary key,
    name        varchar(255) not null,
    description varchar(255) null,
    start_date  date         not null,
    end_date    date         null
)
    engine = MyISAM;

create table questions
(
    id           int auto_increment
        primary key,
    name         varchar(255) not null,
    questionType int          not null
)
    engine = MyISAM;

create table users
(
    id       int auto_increment
        primary key,
    email    varchar(255) null,
    password varchar(255) null
)
    engine = MyISAM;

