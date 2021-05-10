package ru.evteev.poll.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.evteev.poll.enums.QuestionType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "questions")
@RequiredArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.ORDINAL)
    @NotBlank
    QuestionType questionType;

    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 255, message = "Length must be 5-255 symbols")
    @NotBlank
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pool_id")
    Poll poll;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    List<AnswerVariant> answerVariantList;
}
