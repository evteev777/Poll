package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.enums.QuestionType;

@Data
public class QuestionDTO {

    int id;
    int pollId;
    QuestionType questionType;
    String name;
}
