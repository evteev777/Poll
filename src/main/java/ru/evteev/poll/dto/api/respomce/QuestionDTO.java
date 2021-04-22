package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.enums.QuestionType;

import java.util.List;

@Data
public class QuestionDTO {

    int id;
    QuestionType questionType;
    String name;
    List<AnswerVariant> answerVariantList;
}
