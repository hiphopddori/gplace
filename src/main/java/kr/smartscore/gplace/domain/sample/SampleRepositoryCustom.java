package kr.smartscore.gplace.domain.sample;

import com.google.common.annotations.VisibleForTesting;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.web.dto.sample.SampleImageDto;
import kr.smartscore.gplace.web.dto.sample.SampleTeamDto;

import java.util.List;

public interface SampleRepositoryCustom {
    List<Sample> findByEmail(String email);
    List<Sample> findBySample(String email , String name);
    List<SampleTeamDto> findBySampleTeam(String name);
    List<SampleImageDto> findBySampleImage(String name);
    List<SampleImageDto> findBySampleImageUserIdx();
    List<SampleImageDto> findByImageJsonColumn();

}
