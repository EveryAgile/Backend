package org.everyagile.everyagile.dto;

import lombok.Data;
import org.everyagile.everyagile.domain.Importance;

import java.util.List;

@Data
public class SprintRequestDto {
    private String sprintName;
    private Long projectId;
    private String endTime;
    private String description;
    private Importance importance;
}
