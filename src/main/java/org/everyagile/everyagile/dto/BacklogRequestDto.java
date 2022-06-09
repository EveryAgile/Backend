package org.everyagile.everyagile.dto;

import lombok.Getter;
import org.everyagile.everyagile.domain.User;

import java.util.List;

@Getter
public class BacklogRequestDto {
    private String backlogName;
    private Long sprintId;
    private String endTime;
    private Double storyPoint;
    private Double manDay;
    private List<String> users;
}
