package org.ouvio.simple.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO {
    private long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
