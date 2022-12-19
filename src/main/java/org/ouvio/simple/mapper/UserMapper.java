package org.ouvio.simple.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.ouvio.simple.dto.UserDTO;
import org.ouvio.simple.entity.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDTO toDTO(User data);

    User toEntity(UserDTO data);
}
