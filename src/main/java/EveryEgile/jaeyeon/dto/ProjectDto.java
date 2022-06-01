package EveryEgile.jaeyeon.dto;


import EveryEgile.jaeyeon.entity.Project;
import EveryEgile.jaeyeon.entity.Sprint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private Long groupid;

    @Builder.Default
    private List<Sprint> sprints = new ArrayList<>();
    private String projectname;
    private String type;
    private LocalDateTime enddate;
    private LocalDateTime startdate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public Project toEntity() {
        Project project = Project.builder()
                .id(id)
                .groupid(groupid)
                .sprints(sprints)
                .projectname(projectname)
                .type(type)
                .startdate(startdate)
                .enddate(enddate)
                .build();
        return project;
    }

    @Builder
    public ProjectDto( Long id, Long groupid, List<Sprint> sprints , String projectname, String type
    ,LocalDateTime enddate, LocalDateTime startdate,LocalDateTime createdDate, LocalDateTime modifiedDate) {

        this.id = id;
        this.groupid = groupid;
        this.sprints = sprints;
        this.projectname = projectname;
        this.type = type;
        this.enddate = enddate;
        this.startdate = startdate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
