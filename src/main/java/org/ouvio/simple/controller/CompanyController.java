package org.ouvio.simple.controller;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.CompanyDTO;
import org.ouvio.simple.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<CompanyDTO> findAll() {
        log.info("Entrado a metodo: company-findAll");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDTO findById(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: company-findById");
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO save(@RequestBody CompanyDTO data) {
        log.info("Entrado a metodo: company-save");
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody CompanyDTO data) throws Exception {
        log.info("Entrado a metodo: company-update");
        service.update(id, data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws Exception {
        log.info("Entrado a metodo: company-delete");
        service.delete(id);
    }
}
