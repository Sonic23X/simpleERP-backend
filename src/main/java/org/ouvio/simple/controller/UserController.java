package org.ouvio.simple.controller;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.ouvio.simple.dto.NewUserDTO;
import org.ouvio.simple.dto.UserDTO;
import org.ouvio.simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        log.info("Entrado a metodo: user-findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: user-findByid");
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> save(@Valid @RequestBody NewUserDTO data) {
        log.info("Entrado a metodo: user-save");
        return ResponseEntity.created(URI.create("")).body(service.save(data));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@Valid @PathVariable("id") long id, @RequestBody UserDTO data) throws Exception {
        log.info("Entrado a metodo: user-update");
        service.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: user-delete");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
