package org.everyagile.everyagile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="REFRESHTOKEN_ID")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String token;

    public RefreshToken updateToken(String token){
        this.token  =token;
        return this;
    }
}
