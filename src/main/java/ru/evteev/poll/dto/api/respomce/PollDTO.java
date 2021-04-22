package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.entity.Question;

import java.time.LocalDate;
import java.util.List;

@Data
public class PollDTO {

    Integer id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    List<Question> questionList;
}
