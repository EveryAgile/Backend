package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class UserSprint {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @Column(name = "USERSPRINT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SPRINT_ID")
    private Sprint sprint;

    public  UserSprint(User user, Sprint sprint) {
        this.user = user;
        this.sprint = sprint;
    }
}
