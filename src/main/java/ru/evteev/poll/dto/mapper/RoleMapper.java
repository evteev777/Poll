package ru.evteev.poll.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.evteev.poll.dto.api.respomce.RoleDTO;
import ru.evteev.poll.entity.Role;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
}
