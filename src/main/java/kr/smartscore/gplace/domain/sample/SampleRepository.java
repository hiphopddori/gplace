package kr.smartscore.gplace.domain.sample;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// @Repository
public interface SampleRepository extends JpaRepository<Sample, Long> , SampleRepositoryCustom {
    List<Sample> findByName (String name);

    @Query("select s from Sample s left join fetch s.team")
    List<Sample> findByNameFetch ();
}


