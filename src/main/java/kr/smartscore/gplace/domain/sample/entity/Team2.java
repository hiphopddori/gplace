package kr.smartscore.gplace.domain.sample.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "team2")
public class Team2  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="team_code")
    private String teamCode;
    @Column(name="team_name")
    private String teamName;

    @Builder
    public Team2(String teamCode , String teamName) {
        this.teamCode = teamCode;
        this.teamName = teamName;
    }
}
