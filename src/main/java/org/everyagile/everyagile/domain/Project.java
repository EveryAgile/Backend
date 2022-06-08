package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.everyagile.everyagile.dto.ProjectRequestDto;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Collection<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    public Project(ProjectRequestDto requestDto, User user) {
        this.projectName = requestDto.getProjectName();
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();
        this.type = requestDto.getType();
        addUser(user);
    }
}
