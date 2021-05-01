package ru.evteev.poll.dto.api.respomce;

import lombok.Data;
import ru.evteev.poll.entity.Poll;

import java.util.List;

@Data
public class PersonDTO {

    int id;
    String email;
//    String password;
    List<Poll> pollList;
}
