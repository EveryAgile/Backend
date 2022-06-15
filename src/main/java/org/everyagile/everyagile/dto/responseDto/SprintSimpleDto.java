package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.Importance;

import org.everyagile.everyagile.domain.Importance;
import org.everyagile.everyagile.domain.Sprint;

@Getter
public class SprintSimpleDto {
    private Long sprintId;
    private String sprintName;
    private Long projectId;
    private String endTime;
    private String description;
    private Importance importance;
    private boolean status;

    public SprintSimpleDto(Sprint sprint) {
        this.sprintId = sprint.getId();
        this.sprintName = sprint.getSprintName();
        this.projectId = sprint.getProject().getId();
        this.endTime = sprint.getEndTime();
        this.description = sprint.getDescription();
        this.importance = sprint.getImportance();
        this.status = sprint.isStatus();
    }

}
