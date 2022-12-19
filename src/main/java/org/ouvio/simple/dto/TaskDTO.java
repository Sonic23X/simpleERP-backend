package org.ouvio.simple.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private long id;
    private String description;
    private Date limitDate;
    private UserDTO responsible;
    private Date createdAt;
    private Date updatedAt;
}
