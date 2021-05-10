package ru.evteev.poll.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "answer")
@RequiredArgsConstructor
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id", nullable = false)
    Poll poll;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answers_answer_variants",
            joinColumns = @JoinColumn(name = "answer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "answer_variant_id"))
    List<AnswerVariant> answerVariantList;
}
