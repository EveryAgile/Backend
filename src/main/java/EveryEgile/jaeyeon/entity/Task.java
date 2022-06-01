package EveryEgile.jaeyeon.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Task")
//@SuperBuilder
@NoArgsConstructor
public class Task extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "backlogid")
    private Backlog  backlog;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean status ;

    @Column(nullable = true)
    private Double md;

    public Task(String name , boolean status, Long id , Backlog backlog
    , Double md) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.backlog = backlog;
        this.md = md;
    }
}