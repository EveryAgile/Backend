package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.everyagile.everyagile.dto.BacklogRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Backlog extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BACKLOG_ID")
    private Long id;

    @Column(nullable = false)
    private String backlogName;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false)
    private Double storyPoint;

    @Column(nullable = false)
    private Double manDay;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPRINT_ID")
    private Sprint sprint;


    public Backlog(BacklogRequestDto requestDto, boolean status, Sprint sprint) {
        this.backlogName = requestDto.getBacklogName();
        this.endTime = requestDto.getEndTime();
        this.storyPoint = requestDto.getStoryPoint();
        this.manDay = requestDto.getManDay();
        this.status = status;
        this.sprint = sprint;
    }

    public void setBacklogStatus(boolean status){
        this.status = status;
    }
}
