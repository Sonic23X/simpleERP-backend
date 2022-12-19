package org.ouvio.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.NewUserDTO;
import org.ouvio.simple.dto.UserDTO;
import org.ouvio.simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<UserDTO> findAll() {
        log.info("Entrado a metodo: user-findAll");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: user-findByid");
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody NewUserDTO data) {
        log.info("Entrado a metodo: user-findByid");
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody UserDTO data) throws Exception {
        log.info("Entrado a metodo: user-update");
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: user-delete");
        service.delete(id);
    }
}
