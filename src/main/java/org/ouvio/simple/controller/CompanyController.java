package org.ouvio.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.CompanyDTO;
import org.ouvio.simple.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll() {
        log.info("Entrado a metodo: company-findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: company-findById");
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CompanyDTO> save(@RequestBody CompanyDTO data) {
        log.info("Entrado a metodo: company-save");
        return ResponseEntity.created(URI.create("")).body(service.save(data));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody CompanyDTO data) throws Exception {
        log.info("Entrado a metodo: company-update");
        service.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: company-delete");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
