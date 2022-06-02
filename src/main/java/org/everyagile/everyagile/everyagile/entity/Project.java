package org.everyagile.everyagile.everyagile.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Project")
@Builder
@NoArgsConstructor
public class Project extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    @JoinColumn(name = "groupid")
    private Long groupid;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Sprint> sprints = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String projectname;

    @Column(columnDefinition = "TEXT")
    private String type;

    @Column(updatable = true)
    private LocalDateTime enddate;

    @Column(updatable = true)
    private LocalDateTime startdate;


    public Project( Long id, Long groupid, List<Sprint> sprints, String projectname, String type,
                    LocalDateTime enddate, LocalDateTime startdate) {

        this.id = id;
        this.groupid = groupid;
        this.sprints = sprints;
        this.projectname = projectname;
        this.type = type;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Transactional
    public void addSprint(Sprint sprint) {
        this.sprints.add(sprint);
        sprint.setProject(this);
    }
    @Transactional
    public Project removeSprint(Sprint sprint) {
        this.sprints.remove(sprint);
        sprint.setProject(null);

        return this;
    }
}
