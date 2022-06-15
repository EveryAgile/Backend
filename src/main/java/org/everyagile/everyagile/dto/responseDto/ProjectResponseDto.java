package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.ProjType;
import org.everyagile.everyagile.domain.Project;
import org.everyagile.everyagile.domain.Sprint;
import org.everyagile.everyagile.domain.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectResponseDto {
    private Long projectId;
    private String projectName;
    private String startTime;
    private String endTime;
    private ProjType type;
    private List<Long> sprints;


    public ProjectResponseDto(Project project) {
        this.projectId = project.getId();
        this.projectName = project.getProjectName();
        this.startTime = project.getStartTime();
        this.endTime = project.getEndTime();
        this.type = project.getType();
        this.sprints = getList(project.getSprints());
    }

    public List<Long> getList(List<Sprint> sprints){
        List<Long> sprintList = new ArrayList<>();
        for(Sprint sprint: sprints) {
            sprintList.add(sprint.getId());
        }
        return sprintList;
    }
}
