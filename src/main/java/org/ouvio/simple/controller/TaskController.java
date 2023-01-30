package org.ouvio.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.NewTaskDTO;
import org.ouvio.simple.dto.TaskDTO;
import org.ouvio.simple.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        log.info("Entrado a metodo: taks-findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: taks-findById");
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDTO> save(@RequestBody NewTaskDTO data) {
        log.info("Entrado a metodo: taks-save");
        return ResponseEntity.created(URI.create("")).body(service.save(data));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody TaskDTO data) throws Exception {
        log.info("Entrado a metodo: taks-update");
        service.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: taks-delete");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
