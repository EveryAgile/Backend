package EveryEgile.jaeyeon.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Sprint")
//@SuperBuilder
@NoArgsConstructor
public class Sprint extends Sprint_backbone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany
    @ManyToOne( fetch=FetchType.LAZY, targetEntity = Project.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Backlog> backlogs = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column
    private Long importance;

    public Sprint(String name , boolean status, LocalDateTime enddate , Long id
    ,  Project project,List<Backlog> backlogs, String description ,Long importance) {
        super(name , status , enddate);
        this.id = id;
        this.project = project;
        this.backlogs = backlogs;
        this.description = description;
        this.importance = importance;

    }

    public void addBacklog(Backlog backlog) {
        this.backlogs.add(backlog);
        backlog.setSprint(this);
    }

    public Sprint removeBacklog (Backlog backlog) {
        this.backlogs.remove(backlog);
        backlog.setSprint(null);
        return this;
    }

}