package kr.smartscore.gplace.domain.sample.entity;

import lombok.Builder;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name= "user_etc")
public class UserEtc  {
    @Id
    private long userIdx;
    @Column(name= "best_friend")
    private String bestFriend;
    @Column(name= "girl_friend")
    private String girlFriend;

    @MapsId //user_etc.userIdx와 매핑 관계
    @OneToOne
    @JoinColumn(name = "user_id")
    private Sample sample;

    public void use (Sample sample) {
        this.sample = sample;
    }
    @Builder
    public UserEtc (String bestFriend , String girlFriend) {
        this.bestFriend = bestFriend;
        this.girlFriend = girlFriend;
    }
}
