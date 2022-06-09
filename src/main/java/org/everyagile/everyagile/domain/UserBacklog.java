package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class UserBacklog {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "USERBACKLOG_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BACKLOG_ID")
    private Backlog backlog;

    public UserBacklog(User user, Backlog backlog) {
        this.user = user;
        this.backlog =backlog;
    }
}
