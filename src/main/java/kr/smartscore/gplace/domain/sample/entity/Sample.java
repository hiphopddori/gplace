
package kr.smartscore.gplace.domain.sample.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column(nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_Code")
    private Team team;

    @Builder
    public Sample(String name, String email, String picture, String role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    public Sample update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }
    public Sample changeRole(String role) {
        this.role = role;
        return this;
    }
}