package ru.evteev.poll.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.PollDTO;
import ru.evteev.poll.entity.Poll;

@Mapper
public interface PollMapper {

    PollMapper INSTANCE = Mappers.getMapper(PollMapper.class);

    PollDTO toDTO(Poll poll);
}
