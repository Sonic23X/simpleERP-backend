package org.ouvio.simple.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.NewTaskDTO;
import org.ouvio.simple.dto.TaskDTO;
import org.ouvio.simple.entity.Company;
import org.ouvio.simple.entity.Task;
import org.ouvio.simple.entity.User;
import org.ouvio.simple.mapper.TaskMapper;
import org.ouvio.simple.mapper.UserMapper;
import org.ouvio.simple.repository.TaskRepository;
import org.ouvio.simple.repository.UserRepository;
import org.ouvio.simple.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private TaskMapper mapper;
    private TaskRepository repository;
    private UserMapper userMapper;
    private UserRepository userRepository;


    @Autowired
    public TaskServiceImpl(TaskMapper mapper, TaskRepository repository, UserMapper userMapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> findAll() {
        List<Task> tasks = repository.findAll();

        return tasks.stream().map(mapper::toDTO).toList();
    }

    public TaskDTO findById(long id) {
        Optional<Task> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Task task = result.get();
        log.info("Tarea encontrada: " + task.toString());
        return mapper.toDTO(task);
    }

    public TaskDTO save(NewTaskDTO data) {
        Optional<User> result = userRepository.findById(data.getResponsible());

        if (!result.isEmpty()) {
            User user = result.get();

            Task task = new Task();
            task.setDescription(data.getDescription());
            task.setLimitDate(data.getLimitDate());
            task.setResponsible(user);
            task.setCreatedAt(new Date());
            task.setUpdatedAt(new Date());

            log.info("Tarea creada: " + task.toString());

            return mapper.toDTO(repository.save(task));
        }

        log.error("No existe un usuario valido");
        return null;
    }

    public void update(long id, TaskDTO data) throws Exception {
        Optional<Task> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe la tarea");
        }

        Task task = result.get();

        task.setDescription(data.getDescription());
        task.setLimitDate(data.getLimitDate());

        repository.save(task);

        log.info("Tarea actualizada: " + task.toString());
    }

    public void delete(long id) throws Exception {
        Optional<Task> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe la tarea");
        }

        log.info("Tarea eliminada, id: " + id);
        repository.deleteById(id);
    }
}
