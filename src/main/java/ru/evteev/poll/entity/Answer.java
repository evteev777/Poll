package ru.evteev.poll.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
@Entity
@Table(name = "answer")
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
