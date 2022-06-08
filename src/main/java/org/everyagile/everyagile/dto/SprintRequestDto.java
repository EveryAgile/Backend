package org.everyagile.everyagile.dto;

import lombok.Data;
import org.everyagile.everyagile.domain.response.Importance;

@Data
public class SprintRequestDto {
    private String sprintName;
    private Long projectId;
    private String endTime;
    private String description;
    private Importance importance;
}
