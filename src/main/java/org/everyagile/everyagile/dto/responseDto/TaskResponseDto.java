package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.Task;

@Getter
public class TaskResponseDto {
    private Long taskId;
    private String taskName;
    private Long backlogId;
    private Double manDay;
    private boolean status;

    public TaskResponseDto(Task task){
        this.taskId = task.getId();
        this.taskName = task.getTaskName();
        this.backlogId = task.getBacklog().getId();
        this.manDay = task.getManDay();
        this.status = task.isStatus();
    }
}
