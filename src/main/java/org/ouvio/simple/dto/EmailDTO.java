package org.ouvio.simple.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private String subject;
    private String to;
    private String from;
    private String body;
}
