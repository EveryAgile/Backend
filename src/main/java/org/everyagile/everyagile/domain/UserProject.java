package org.everyagile.everyagile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class UserProject {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @Column(name = "USERPROJECT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    public UserProject(User user, Project project) {
        this.user = user;
        this.project = project;
    }
}
