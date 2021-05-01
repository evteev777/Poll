package ru.evteev.poll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.PersonDTO;
import ru.evteev.poll.entity.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(Person person);
}
