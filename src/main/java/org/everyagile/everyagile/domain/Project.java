package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.everyagile.everyagile.dto.ProjectRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Project extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjType type;

    @ManyToMany()
    private List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Sprint> sprints = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public Project(ProjectRequestDto requestDto, User user) {
        this.projectName = requestDto.getProjectName();
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();
        this.type = requestDto.getType();
        addUser(user);
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public void deleteSprint(Sprint sprint) {
        sprints.remove(sprint);
    }
}
