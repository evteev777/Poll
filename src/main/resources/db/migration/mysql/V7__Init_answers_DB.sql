create table if not exists answer
(
    id          int auto_increment
        primary key,
    person_id   int not null,
    poll_id     int not null,
    question_id int not null,
    constraint FK7mluqeoggakn10mca9veouh1p
        foreign key (question_id) references questions (id),
    constraint FKov29nqfe0683bp4m3c3teqmom
        foreign key (poll_id) references polls (id),
    constraint FKrmicuuujjcelbi32f2kt3llv3
        foreign key (person_id) references persons (id)
);

create table if not exists answers_answer_variants
(
    answer_id         int not null,
    answer_variant_id int not null,
    constraint FKfha2l56k2g9cs5g5imc1yxekq
        foreign key (answer_id) references answer (id),
    constraint FK7711dnqgwajjgs7j2p9cqif9p
        foreign key (answer_variant_id) references answer_variants (id)
);
