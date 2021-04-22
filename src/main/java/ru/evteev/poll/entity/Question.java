package ru.evteev.poll.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.ORDINAL)
    @NotBlank
    QuestionType questionType;

    @Column(name = "name")
    @Size(min = 5, max = 255, message = "Length must be 5-255 symbols")
    @NotBlank
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<AnswerVariant> answerVariantList;
}
