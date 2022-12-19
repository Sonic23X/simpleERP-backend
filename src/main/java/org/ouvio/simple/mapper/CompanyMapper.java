package org.ouvio.simple.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.ouvio.simple.dto.CompanyDTO;
import org.ouvio.simple.entity.Company;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CompanyMapper {

    CompanyDTO toDTO(Company data);

    Company toEntity(CompanyDTO data);
}
