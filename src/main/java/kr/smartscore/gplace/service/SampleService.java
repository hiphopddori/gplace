package kr.smartscore.gplace.service;

import kr.smartscore.gplace.domain.sample.Image2Repository;
import kr.smartscore.gplace.domain.sample.ImageRepository;
import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.entity.*;
import kr.smartscore.gplace.infrastructure.dao.sample.SampleMapper;
import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
// import kr.smartscore.gplace.web.dto.sample.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {

    // private final ImageRepository imageRepository;
    private final Image2Repository image2Repository;
    private final SampleMapper sampleMapper;
    private final SampleRepository sampleRepository;

    @Transactional
    public SampleVo findeById(long id) {
        SampleVo sampleVo = sampleMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return sampleVo;
    }
    @Transactional
    public void regImage(Long userIdx ) {
        /*
        Sample sample = sampleRepository.findById(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userIdx));

        Image image = new Image();
        image.setImageUrl("/user/save1");
        image.setImageName("save test6");
        sample.addImage(image);
        sampleRepository.save(sample);
        */
        /* User 정보 findBy 없이 FK 서정
         */
        User user = User.builder().id(22L).build();
        Image2 image = new Image2();
        image.setImageUrl("/user/save11");
        image.setImageName("save test11");
        image.setUser(user);
        image2Repository.save(image);

    }
    @Transactional
    public Sample addSample() {
        Sample sample = Sample.builder()
                .email("sunsee78@gmail.com")
                .picture("/image/test.jps")
                .name("김인철")
                .role("ADMIN")
                .build();

        sampleRepository.save(sample);
        return sample;
    }
    @Transactional
    public Image addImage() {
        Optional<Sample> mayBeSample = sampleRepository.findById(1L);
        Image image = new Image();
        image.setImageName("대표이미지");
        image.setImageUrl("/image/base.jpg");
        mayBeSample.ifPresent(sample -> {
            sample.addImage(image);
        });
        sampleRepository.flush();

        // sampleRepository.save(mayBeSample.get());
        return image;
    }

    @Transactional
    public Image addImageAndMetaData() {
        Optional<Sample> mayBeSample = sampleRepository.findById(1L);
        Image image = new Image();
        image.setImageName("대표이미지");
        image.setImageUrl("/image/base.jpg");

        image.addMetaInfo("area","chungchundong")
                .addMetaInfo("phone", "01063604604");


        mayBeSample.ifPresent(sample -> {
            sample.addImage(image);
        });
        // sampleRepository.flush();

        // sampleRepository.save(mayBeSample.get());
        return image;
    }

    @Transactional
    public Image addImageAndMetaDataList() {
        Optional<Sample> mayBeSample = sampleRepository.findById(1L);
        Image image = new Image();
        image.setImageName("대표이미지");
        image.setImageUrl("/image/base.jpg");
        /*
        image.addMetaList("chungchundong", "01063604604")
                .addMetaList("gurodigital", "01022223333");
        */


        image.addMetaInfos("chungchundong","01063604603")
                .addMetaInfos("gudi", "01022223333");

        mayBeSample.ifPresent(sample -> {
            sample.addImage(image);
        });
        return image;
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


    public void One2OneTest(long id) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        // Phone phone = sample.getPhone();
        Phone phones = sample.getPhone();
        // String pNumber = phone.getPhone();
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
