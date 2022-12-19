package org.ouvio.simple.service.impl;

import org.ouvio.simple.dto.NewProjectDTO;
import org.ouvio.simple.dto.ProjectDTO;
import org.ouvio.simple.entity.Company;
import org.ouvio.simple.entity.Project;
import org.ouvio.simple.mapper.CompanyMapper;
import org.ouvio.simple.mapper.ProjectsMapper;
import org.ouvio.simple.repository.CompanyRepository;
import org.ouvio.simple.repository.ProjectRepository;
import org.ouvio.simple.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectsMapper mapper;
    private CompanyMapper companyMapper;
    private ProjectRepository repository;
    private CompanyRepository companyRepository;

    @Autowired
    public ProjectServiceImpl(ProjectsMapper mapper, CompanyMapper companyMapper, ProjectRepository repository, CompanyRepository companyRepository) {
        this.mapper = mapper;
        this.companyMapper = companyMapper;
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public List<ProjectDTO> findAll() {
        List<Project> projects = repository.findAll();

        return projects.stream().map(mapper::toDTO).toList();
    }

    public ProjectDTO findById(long id) {
        Optional<Project> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Project project = result.get();

        log.info("Proyecto encontrado: " + project.toString());
        return mapper.toDTO(project);
    }

    public ProjectDTO save(NewProjectDTO data) {
        Optional<Company> result = companyRepository.findById(data.getCompany());

        if (!result.isEmpty()) {

            Company company = result.get();

            Project project = new Project();
            project.setName(data.getName());
            project.setCompany(company);
            project.setCreatedAt(new Date());
            project.setUpdatedAt(new Date());

            log.info("Proyecto creado: " + project.toString());
            return mapper.toDTO(repository.save(project));
        }

        log.error("No existe una compañia valida");
        return null;
    }

    public void update(long id, ProjectDTO data) throws Exception {
        Optional<Project> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe el proyecto");
        }

        Project project = result.get();

        project.setName(data.getName());
        project.setUpdatedAt(new Date());

        repository.save(project);

        log.info("Proyecto actualizado: " + project.toString());
    }

    public void delete(long id) throws Exception {
        Optional<Project> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe el proyecto");
        }

        log.info("Proyecto eliminado, id: " + id);
        repository.deleteById(id);
    }
}
