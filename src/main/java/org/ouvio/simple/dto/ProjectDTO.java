package org.ouvio.simple.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {
    private long id;
    private String name;
    private CompanyDTO company;
    private Date createdAt;
    private Date updatedAt;
}
