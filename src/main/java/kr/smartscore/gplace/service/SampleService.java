package kr.smartscore.gplace.service;

import kr.smartscore.gplace.domain.sample.ImageRepository;
import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.entity.Image;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.domain.sample.entity.UserEtc;
import kr.smartscore.gplace.infrastructure.dao.sample.SampleMapper;
import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
// import kr.smartscore.gplace.web.dto.sample.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final ImageRepository imageRepository;
    private final SampleMapper sampleMapper;
    private final SampleRepository sampleRepository;

    public SampleVo findeById(long id) {
        SampleVo sampleVo = sampleMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return sampleVo;
    }
    @Transactional
    public void regImage(Long userIdx ) {
        Sample sample = sampleRepository.findById(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userIdx));
        Image image = new Image();
        image.setImageUrl("/user/save1");
        image.setImageName("save test6");
        sample.addImage(image);
        sampleRepository.save(sample);
    }
    @Transactional
    public void changeImage() {
        Sample sample = sampleRepository.findByName("SYKKIM").get(0);
        Image chnageImage = sample.getImages().get(0);
        chnageImage.setImageUrl("/user/save1/test");
        sampleRepository.save(sample);
    }

    @Transactional
    public void changeImageById(Long userIdx , Long imageIdx) {
        Sample sample = sampleRepository.findByImageById(userIdx, imageIdx);
        Image image = sample.getImages().get(0);
        image.setImageName("changeImage");
        // sampleRepository.save(sample);
    }

    @Transactional
    public void removeImageById(Long userIdx , Long imageIdx) {
        Sample sample = sampleRepository.findByImageById(userIdx, imageIdx);
        Image image = sample.getImages().get(0);
        // sample.getImages().remove(0);
        // image = null;
        // sample.getImages().remove(0);
        sample.removeImage(image);
        // image.setSample(null);
        // sampleRepository.save(sample);
    }

    @Transactional
    public void regEtc(long id)  {
        // OneToOne 관계 layz 문제점으로 DB 구조 변경 필요
        // 이유 : https://medium.com/@yongkyu.jang/jpa-%EB%8F%84%EC%9E%85-onetoone-%EA%B4%80%EA%B3%84%EC%97%90%EC%84%9C%EC%9D%98-lazyloading-%EC%9D%B4%EC%8A%88-1-6d19edf5f4d3

        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        // sample.orElse(null);
        UserEtc changeEtc =  UserEtc.builder()
                .bestFriend("암살소빵")
                // .sample(sample)
                .build();

        sample.changeEtc(changeEtc);
        sampleRepository.save(sample);
    }

    @Transactional
    public void deleteEtc(long id)  {
        // OneToOne 관계 layz 문제점으로 DB 구조 변경 필요
        // 이유 : https://medium.com/@yongkyu.jang/jpa-%EB%8F%84%EC%9E%85-onetoone-%EA%B4%80%EA%B3%84%EC%97%90%EC%84%9C%EC%9D%98-lazyloading-%EC%9D%B4%EC%8A%88-1-6d19edf5f4d3

        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        sample.removeEtc();
        sampleRepository.save(sample);
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
