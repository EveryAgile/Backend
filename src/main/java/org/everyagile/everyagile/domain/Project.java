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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PROJECT_ID")
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

    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<Sprint>();

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public Project(ProjectRequestDto requestDto) {
        this.projectName = requestDto.getProjectName();
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();
        this.type = requestDto.getType();
    }

}
