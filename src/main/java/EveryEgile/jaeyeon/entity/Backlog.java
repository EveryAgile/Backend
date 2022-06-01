package EveryEgile.jaeyeon.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Backlog")
//@SuperBuilder
@NoArgsConstructor
public class Backlog extends Sprint_backbone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sprintid")
    private Sprint sprint;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @Column(nullable = true)
    private Double storypoint;

    @Column(nullable = true)
    private Double md;

    public Backlog(String name, boolean status, LocalDateTime enddate, Long id
            , Sprint sprint, List<Task> tasks, Double storypoint, Double md) {
        super(name, status, enddate);
        this.id = id;
        this.sprint = sprint;
        this.tasks = tasks;
        this.storypoint = storypoint;
        this.md = md;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setBacklog(this);
    }

    @Transactional
    public Backlog removeTask(Task task) {
        tasks.remove(task);
        task.setBacklog(null);
        return this;
    }
}
