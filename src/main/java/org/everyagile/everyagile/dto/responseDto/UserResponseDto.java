package org.everyagile.everyagile.dto.responseDto;

import lombok.Builder;
import lombok.Data;
import org.everyagile.everyagile.domain.User;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
