package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="SPRINT_ID")
    private Long id;

    @Column(nullable = false)
    private String sprintName;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Importance importance;

    // 완료 여부
    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    @OneToMany(mappedBy = "sprint")
    private List<Backlog> backlogs = new ArrayList<Backlog>();

    public Sprint(SprintRequestDto requestDto, boolean status, Project project) {
        this.sprintName = requestDto.getSprintName();
        this.endTime = requestDto.getEndTime();
        this.description = requestDto.getDescription();
        this.importance = requestDto.getImportance();
        this.status = status;
        this.project = project;
    }

    public void addBacklog(Backlog backlog) {
        this.backlogs.add(backlog);
    }

    public void setSprintStatus(boolean status){
        this.status = status;
    }
}
