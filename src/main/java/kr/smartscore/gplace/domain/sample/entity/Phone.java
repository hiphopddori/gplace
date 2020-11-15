package kr.smartscore.gplace.domain.sample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    // @Column
    // private Long targetId;
    @Column
    private String target;
    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name="targetId")
    @Where(clause = "target = 'SELF'")
    private Sample sample;
}
