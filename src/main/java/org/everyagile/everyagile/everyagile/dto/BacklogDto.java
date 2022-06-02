package org.everyagile.everyagile.everyagile.dto;

import org.everyagile.everyagile.everyagile.entity.Sprint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.everyagile.everyagile.everyagile.entity.Backlog;
import org.everyagile.everyagile.everyagile.entity.Task;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@SuperBuilder
public class BacklogDto {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String name;
    private LocalDateTime enddate;
    private boolean status ;

    private Long id;
    private Sprint sprint;

    @Builder.Default
    private List<Task> tasks = new ArrayList<>();
    private Double storypoint;
    private Double md;

    public Backlog toEntity() {
        Backlog backlog = new Backlog(name , status, enddate , id
                , sprint , tasks, storypoint , md);
        return backlog;
    }

    @Builder
    BacklogDto(Long id , Sprint sprint ,List<Task> tasks, Double storypoint , Double md , String name , LocalDateTime enddate,
               boolean status , LocalDateTime createdDate , LocalDateTime modifiedDate){
        this.id = id;
        this.sprint = sprint;
        this.tasks = tasks;
        this.storypoint = storypoint;
        this.md = md;
        this.name = name;
        this. enddate = enddate;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}
