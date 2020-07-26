package kr.smartscore.gplace.web;

import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleJpaTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void Sample_QueryDsl_Test () {
        // given
        String findEmail = "developerkorea@gmail.com";
        // String findName = "DDORI";
        String findName = "";
        // when
        // List<Sample> samples = sampleRepository.findByEmail(findEmail);
        List<Sample> samples = sampleRepository.findBySample(findEmail, findName);
        //then
        Boolean isData = samples.size() > 0;
        assertThat(isData, is(true));
    }
    @Test
    public void Sample_레파지토리_팩토리_Test () {
        // given
        String findName = "grukim";
        // when
        List<Sample> samples = sampleRepository.findByName(findName);
        //then
        Boolean isData = samples.size() == 3;
        assertThat(isData, is(true));
    }


}
