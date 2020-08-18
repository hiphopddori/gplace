package kr.smartscore.gplace.domain.sample;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// @Repository
public interface SampleRepository extends JpaRepository<Sample, Long> , SampleRepositoryCustom {
    Sample findById(String id);
    List<Sample> findByName (String name);

    @Query("select s from Sample s left join fetch s.team2")
    List<Sample> findByNameFetch ();

    // fetch로 조인을 해야지만 getImages를 호출해도 원하는 idx값을 가져온다.
    // fetch로 안할경우 getImages 호출시 프록시 되어 다시 가져옴
    @Query("SELECT s FROM Sample s join fetch s.images i WHERE s.id = :userIdx and i.idx = :imageIdx")
    Sample findByImageById (
            @Param("userIdx") Long userIdx,
            @Param("imageIdx") Long imageIdx);
}


