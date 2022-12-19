package org.ouvio.simple.service;

import org.ouvio.simple.dto.NewProjectDTO;
import org.ouvio.simple.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> findAll();

    ProjectDTO findById(long id);

    ProjectDTO save(NewProjectDTO data);

    void update(long id, ProjectDTO data) throws Exception;

    void delete(long id) throws Exception;
}
