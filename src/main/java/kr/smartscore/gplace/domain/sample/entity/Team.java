package kr.smartscore.gplace.domain.sample.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    //private long idx;
    @Id
    private String teamCode;
    @Column
    private String teamName;

    @Builder
    public Team(String teamCode , String teamName) {
        this.teamCode = teamCode;
        this.teamName = teamName;
    }
}
