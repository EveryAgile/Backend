package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.Backlog;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.Importance;
import org.everyagile.everyagile.domain.Task;

import java.util.ArrayList;
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
    private List<Long> backlogList;


    public SprintResponseDto(Sprint sprint) {
        this.sprintId = sprint.getId();
        this.sprintName = sprint.getSprintName();
        this.endTime = sprint.getEndTime();
        this.description = sprint.getDescription();
        this.importance = sprint.getImportance();
        this.status = sprint.isStatus();
        this.projectId = sprint.getProject().getId();
        this.backlogList = getList(sprint.getBacklogs());
    }

    public List<Long> getList(List<Backlog> backlogs){
        List<Long> taskList = new ArrayList<>();
        for(Backlog backlog : backlogs) {
            taskList.add(backlog.getId());
        }
        return taskList;
    }
}
