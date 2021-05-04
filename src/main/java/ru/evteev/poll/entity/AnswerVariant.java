package ru.evteev.poll.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "answer_variants")
public class AnswerVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "text", nullable = false)
    @NotBlank
    String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answers_answer_variants",
            joinColumns = @JoinColumn(name = "answer_variant_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    List<Answer> answerList;
}
