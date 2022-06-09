package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class UserTask {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "USERTASK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private Task task;

    public UserTask(User user ,Task task){
        this.user = user;
        this.task = task;
    }

}
