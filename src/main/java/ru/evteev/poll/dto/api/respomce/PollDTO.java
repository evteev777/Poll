package ru.evteev.poll.dto.api.respomce;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PollDTO {

    Integer id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
}
