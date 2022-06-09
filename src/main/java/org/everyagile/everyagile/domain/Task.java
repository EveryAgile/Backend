package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.everyagile.everyagile.dto.TaskRequestDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Task extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TASK_ID")
    private Long id;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private Double manDay;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Backlog backlog;

    public Task(TaskRequestDto requestDto, boolean status, Backlog backlog){
        this.taskName = requestDto.getTaskName();
        this.manDay = requestDto.getManDay();
        this.status = status;
        this.backlog = backlog;
    }

    public void setTaskStatus(boolean status) {
        this.status = status;
    }
}
