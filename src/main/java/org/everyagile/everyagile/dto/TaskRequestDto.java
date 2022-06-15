package org.everyagile.everyagile.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskRequestDto {
    private String taskName;
    private Long backlogId;
    private Double manDay;
//    private List<String> users;
}
