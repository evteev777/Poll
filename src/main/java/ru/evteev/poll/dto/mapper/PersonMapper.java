package ru.evteev.poll.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.PersonDTO;
import ru.evteev.poll.entity.Person;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "email", source = "user.username")
    @Mapping(target = "answers", ignore = true)
    PersonDTO toDTO(Person person);
}
