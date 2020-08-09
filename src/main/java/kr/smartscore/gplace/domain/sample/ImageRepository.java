package kr.smartscore.gplace.domain.sample;

import kr.smartscore.gplace.domain.sample.entity.Image;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByIdx(Long idx);
}
