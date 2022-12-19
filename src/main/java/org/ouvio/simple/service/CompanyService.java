package org.ouvio.simple.service;

import org.ouvio.simple.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> findAll();

    CompanyDTO findById(long id);

    CompanyDTO save(CompanyDTO data);

    void update(long id, CompanyDTO data) throws Exception;

    void delete(long id) throws Exception;
}
