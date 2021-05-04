package ru.evteev.poll.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.QuestionDTO;
import ru.evteev.poll.entity.Question;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "pollId", source = "poll.id")
    QuestionDTO toDTO(Question question);
}
