package ru.evteev.poll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.AnswerDTO;
import ru.evteev.poll.entity.Answer;

@Mapper
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AnswerDTO toDTO(Answer answer);
}
