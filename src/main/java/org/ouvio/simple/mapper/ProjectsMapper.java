package org.ouvio.simple.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.ouvio.simple.dto.ProjectDTO;
import org.ouvio.simple.entity.Project;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectsMapper {

    ProjectDTO toDTO(Project data);

    Project toEntity(ProjectDTO data);
}
