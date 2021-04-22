package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.entity.Poll;
import ru.evteev.poll.entity.Question;
import ru.evteev.poll.entity.User;

@Data
public class AnswerDTO {

    int id;
    User user;
    Poll poll;
    Question question;
    String answerText;
}
