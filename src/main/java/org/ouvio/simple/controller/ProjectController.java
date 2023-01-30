package org.ouvio.simple.controller;

import org.ouvio.simple.dto.ProjectDTO;
import org.ouvio.simple.dto.NewProjectDTO;
import org.ouvio.simple.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll() {
        log.info("Entrado a metodo: project-findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: project-findById");
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectDTO> save(@RequestBody NewProjectDTO data) {
        log.info("Entrado a metodo: project-save");
        return ResponseEntity.created(URI.create("")).body(service.save(data));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ProjectDTO data) throws Exception {
        log.info("Entrado a metodo: project-update");
        service.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: project-delete");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
