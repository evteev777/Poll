package ru.evteev.poll.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    Poll poll;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    Question question;

    @Column(name = "answer")
    String answerText;
}
