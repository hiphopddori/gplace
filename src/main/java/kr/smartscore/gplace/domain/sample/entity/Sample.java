
package kr.smartscore.gplace.domain.sample.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
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
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_Code")
    private Team team;
    */

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="team_code", referencedColumnName = "team_code")
    @JoinColumn(name="team_code")
    private Team2 team2;

    //orphanRemoval = true , cascade = CascadeType.ALL 두개다 줘야 실제 삭제가 됨

    @Builder.Default
    @OneToMany(mappedBy = "sample", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true  )
    @JsonIgnore
    private List<Image> images = new ArrayList<Image>();

    @OneToOne(mappedBy = "sample", cascade = CascadeType.ALL, orphanRemoval = true)
    // @LazyToOne(LazyToOneOption.NO_PROXY)
    private UserEtc userEtc;

    @OneToOne(mappedBy = "sample")
    @Where(clause = "target = 'SELF'")
    private Phone phone;
    public boolean addImage(Image image) {
        if (isImageValidatChk(image)) {
            image.setSample(this);
            images.add(image);
            // image.setSample(this);
            return true;
        }
        return false;
    }

    public void removeImage(Image image) {
        // image.setSample(null);
        this.images.remove(image);
    }

    public void changeEtc(UserEtc etc) {
        userEtc = etc;
        userEtc.use(this);
    }

    public void removeEtc() {
        userEtc = null;
        // userEtc.use(null);
    }

    public boolean isImageValidatChk(Image image) {
        return true;
    }

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