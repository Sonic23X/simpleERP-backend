package org.ouvio.simple.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String password;
    private String type;
    private CompanyDTO company;
    private Date createdAt;
    private Date updatedAt;
}
