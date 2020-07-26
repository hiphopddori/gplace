package kr.smartscore.gplace.domain.sample;

import com.google.common.annotations.VisibleForTesting;
import kr.smartscore.gplace.domain.sample.entity.Sample;

import java.util.List;

public interface SampleRepositoryCustom {
    List<Sample> findByEmail(String email);
    List<Sample> findBySample(String email , String name);
}
