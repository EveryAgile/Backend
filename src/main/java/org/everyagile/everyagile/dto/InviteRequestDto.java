package org.everyagile.everyagile.dto;

import lombok.Data;

@Data
public class InviteRequestDto {
    private Long projectId;
    private String memberEmail;
}
