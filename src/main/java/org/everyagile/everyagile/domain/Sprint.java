package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.everyagile.everyagile.domain.response.Importance;
import org.everyagile.everyagile.dto.SprintRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Sprint extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String springName;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Importance importance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToMany()
    private List<User> users = new ArrayList<>();

    // 완료 여부
    @Column(nullable = false)
    private boolean status;

    public void addUser(User user) {
        this.users.add(user);
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }

    public Sprint(SprintRequestDto requestDto, boolean status, Project project) {
        this.springName = requestDto.getSprintName();
        this.endTime = requestDto.getEndTime();
        this.description = requestDto.getDescription();
        this.importance = requestDto.getImportance();
        this.status = status;
        this.project = project;
    }

    public void setSprintStatus(boolean status){
        this.status = status;
    }
}
