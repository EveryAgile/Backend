package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.Backlog;
import org.everyagile.everyagile.domain.Task;

import java.util.List;

@Getter
public class BacklogResponseDto {
    private Long backlogId;
    private String backlogName;
    private Long sprintId;
    private String endTime;
    private Double storyPoint;
    private Double manDay;
    private boolean status;
    private List<Task> tasks;

    public BacklogResponseDto(Backlog backlog) {
        this.backlogId = backlog.getId();
        this.backlogName = backlog.getBacklogName();
        this.sprintId = backlog.getSprint().getId();
        this.endTime = backlog.getEndTime();
        this.storyPoint = backlog.getStoryPoint();
        this.manDay = backlog.getManDay();
        this.status = backlog.isStatus();
        this.tasks = backlog.getTasks();
    }
}
