package ru.evteev.poll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.AnswerVariantDTO;
import ru.evteev.poll.entity.AnswerVariant;

@Mapper
public interface AnswerVariantMapper {

    AnswerVariantMapper INSTANCE = Mappers.getMapper(AnswerVariantMapper.class);

    AnswerVariantDTO toDTO(AnswerVariant answerVariant);
}
