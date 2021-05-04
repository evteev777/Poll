package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.entity.AnswerVariant;
import ru.evteev.poll.entity.Person;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;

import java.util.List;

@Data
public class AnswerDTO {

    int id;
    Person person;
    Poll poll;
    Question question;
    List<AnswerVariant> answerVariantList;
}
