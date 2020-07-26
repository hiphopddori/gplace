package kr.smartscore.gplace.service;

import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.infrastructure.dao.sample.SampleMapper;
import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
// import kr.smartscore.gplace.web.dto.sample.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleMapper sampleMapper;
    // private final SampleRepository sampleRepository;

    public SampleVo findeById(long id) {
        SampleVo sampleVo = sampleMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return sampleVo;
    }
    /*
    @Transactional
    public long reg(SampleSaveRequestDto requestDto) {
        return sampleRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public void delete(long id) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        sampleRepository.delete(sample);
    }
     */
}
