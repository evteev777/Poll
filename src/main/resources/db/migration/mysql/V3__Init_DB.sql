create table answer
(
    id          int auto_increment
        primary key,
    person_id   int           not null,
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
    on answer (person_id);

alter table answer
    add constraint FKrmicuuujjcelbi32f2kt3llv3
        foreign key (person_id) references persons (id);

alter table answer
    add constraint FKov29nqfe0683bp4m3c3teqmom
        foreign key (poll_id) references polls (id);

alter table answer
    add constraint FK7mluqeoggakn10mca9veouh1p
        foreign key (question_id) references questions (id);

create table answer_variants
(
    id   int auto_increment
        primary key,
    text varchar(255) null
)
    engine = MyISAM;

alter table answer_variants
    add constraint FKa6tlpys1xjvwp7bjmpoet0xyy
        foreign key (id) references questions (id);

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

alter table polls
    add constraint FKq2rt5ps1yua3ofmscvdeckxbt
        foreign key (id) references persons (id);

create table questions
(
    id           int auto_increment
        primary key,
    name         varchar(255) not null,
    questionType int          not null
)
    engine = MyISAM;

alter table questions
    add constraint FKgwrpsd5sa2q545j8c4mitypsd
        foreign key (id) references polls (id);

create table persons
(
    id    int auto_increment
        primary key,
    email varchar(255) null
)
    engine = MyISAM;

