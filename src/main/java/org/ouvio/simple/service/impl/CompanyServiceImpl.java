package org.ouvio.simple.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.CompanyDTO;
import org.ouvio.simple.entity.Company;
import org.ouvio.simple.mapper.CompanyMapper;
import org.ouvio.simple.repository.CompanyRepository;
import org.ouvio.simple.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyMapper mapper;
    private CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyMapper mapper, CompanyRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<CompanyDTO> findAll() {
        List<Company> projects = repository.findAll();

        return projects.stream().map(mapper::toDTO).toList();
    }

    public CompanyDTO findById(long id) {
        Optional<Company> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Company company = result.get();

        log.info("Compañia encontrada: " + company.toString());
        return mapper.toDTO(company);
    }

    public CompanyDTO save(CompanyDTO data) {
        Company entity = mapper.toEntity(data);
        entity.setCreatedAt(new Date());
        entity.setUpdatedAt(new Date());

        log.info("Compañia creada: " + entity.toString());
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, CompanyDTO data) throws Exception {
        Optional<Company> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe la empresa");
        }

        Company company = result.get();

        company.setName(data.getName());
        company.setUpdatedAt(new Date());

        repository.save(company);

        log.info("Compañia actualizada: " + company.toString());
    }

    public void delete(long id) throws Exception {
        Optional<Company> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe la empresa");
        }

        log.info("Compañia eliminada, id: " + id);
        repository.deleteById(id);
    }
}
