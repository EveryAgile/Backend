package EveryEgile.jaeyeon.dto;

import EveryEgile.jaeyeon.entity.Project;
import EveryEgile.jaeyeon.entity.Sprint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import EveryEgile.jaeyeon.entity.Backlog;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
public class SprintDto {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    private String name;
    private LocalDateTime enddate;
    private boolean status ;

    private Long id;
    private Project project;
    @Builder.Default
    private List<Backlog> backlogs = new ArrayList<>();
    private String description;
    private Long importance;

    public Sprint toEntity(){
        Sprint sprint = new Sprint(name , status, enddate , id
                , project, backlogs, description, importance);
        return sprint;
    }

    @Builder
    public SprintDto (Long id, Project project, List<Backlog> backlogs ,String description, Long importance,
                      String name , LocalDateTime enddate, boolean status, LocalDateTime createdDate,
                      LocalDateTime modifiedDate) {
        this.id = id;
        this.project = project;
        this.backlogs = backlogs;
        this.description = description;
        this.importance = importance;
        this.name = name;
        this.enddate = enddate;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}
