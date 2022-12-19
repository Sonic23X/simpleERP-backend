package org.ouvio.simple.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.ouvio.simple.dto.TaskDTO;
import org.ouvio.simple.entity.Task;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    TaskDTO toDTO(Task data);

    Task toEntity(TaskDTO data);
}
