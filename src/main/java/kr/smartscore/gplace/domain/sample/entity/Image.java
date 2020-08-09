package kr.smartscore.gplace.domain.sample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name="image_name")
    private String imageName;
    @Column(name="image_url")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Sample sample;
}
