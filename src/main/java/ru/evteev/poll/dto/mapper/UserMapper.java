package ru.evteev.poll.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.UserDTO;
import ru.evteev.poll.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
}
