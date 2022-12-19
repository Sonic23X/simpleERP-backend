package org.ouvio.simple.dto;

import lombok.Data;

@Data
public class NewUserDTO {
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String password;
    private String type;
    private Long company;
}
