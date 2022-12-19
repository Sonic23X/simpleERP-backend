package org.ouvio.simple.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewTaskDTO {
    private String description;
    private Date limitDate;
    private long responsible;
}
