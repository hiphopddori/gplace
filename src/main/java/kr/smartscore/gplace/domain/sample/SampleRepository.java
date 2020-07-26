package kr.smartscore.gplace.domain.sample;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// @Repository
public interface SampleRepository extends JpaRepository<Sample, Long> , SampleRepositoryCustom {
    List<Sample> findByName (String name);
}


