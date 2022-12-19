package org.ouvio.simple.service;

import org.ouvio.simple.dto.NewTaskDTO;
import org.ouvio.simple.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> findAll();

    TaskDTO findById(long id);

    TaskDTO save(NewTaskDTO data);

    void update(long id, TaskDTO data) throws Exception;

    void delete(long id) throws Exception;
}
