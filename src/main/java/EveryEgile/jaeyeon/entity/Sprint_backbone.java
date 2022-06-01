package EveryEgile.jaeyeon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
//@SuperBuilder
@NoArgsConstructor
public class Sprint_backbone extends Time {

    @Column(length = 20, nullable = false)
    private String name;

    @Column(updatable = true)
    private LocalDateTime enddate;

    @Column(nullable = false)
    private boolean status ;

    public Sprint_backbone( String name , boolean status , LocalDateTime enddate){
        this.name = name;
        this.status = status;
        this.enddate = enddate;
    }
}
