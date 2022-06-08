package org.everyagile.everyagile.dto.responseDto;

import lombok.Getter;
import org.everyagile.everyagile.domain.ProjType;
import org.everyagile.everyagile.domain.Project;

@Getter
public class ProjectResponseDto {
    private Long projectId;
    private String projectName;
    private String startTime;
    private String endTime;
    private ProjType type;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getId();
        this.projectName = project.getProjectName();
        this.startTime = project.getStartTime();
        this.endTime = project.getEndTime();
        this.type = project.getType();
    }
}
