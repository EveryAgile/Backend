package org.everyagile.everyagile.dto;

import lombok.Data;
import org.everyagile.everyagile.domain.ProjType;

@Data
public class ProjectRequestDto {
    private String projectName;
    private String startTime;
    private String endTime;
    private ProjType type;
}
