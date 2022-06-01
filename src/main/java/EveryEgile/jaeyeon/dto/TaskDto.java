package EveryEgile.jaeyeon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import EveryEgile.jaeyeon.entity.Backlog;
import EveryEgile.jaeyeon.entity.Task;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
public class TaskDto {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long id;
    private Backlog backlog;
    private String name;
    private boolean status ;
    private Double md;

    public Task toEntity() {
        Task task = new Task(name , status, id ,backlog
                , md);
        return task;
    }

    @Builder
    public TaskDto(Long id , Backlog backlog , String name , boolean status, Double md ,
                   LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.backlog = backlog;
        this.name = name;
        this.status = status;
        this.md = md;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
