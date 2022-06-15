package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.Backlog;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.Importance;

import java.util.List;

@Getter
public class SprintResponseDto {
    private Long sprintId;
    private String sprintName;
    private Long projectId;
    private String endTime;
    private String description;
    private Importance importance;
    private boolean status;


    public SprintResponseDto(Sprint sprint) {
        this.sprintId = sprint.getId();
        this.sprintName = sprint.getSprintName();
        this.endTime = sprint.getEndTime();
        this.description = sprint.getDescription();
        this.importance = sprint.getImportance();
        this.status = sprint.isStatus();
        this.projectId = sprint.getProject().getId();

    }
}
