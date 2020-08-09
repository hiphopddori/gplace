package kr.smartscore.gplace.web;

import kr.smartscore.gplace.domain.sample.ImageRepository;
import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.TeamRepository;
import kr.smartscore.gplace.domain.sample.entity.Image;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.domain.sample.entity.Team;
import kr.smartscore.gplace.domain.sample.entity.Team2;
import kr.smartscore.gplace.web.dto.sample.SampleImageDto;
import kr.smartscore.gplace.web.dto.sample.SampleTeamDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SampleRepository sampleRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ImageRepository imageRepository;

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
    @Test
    @Transactional
    public void Sample_Lazy_Test () {
        List<Sample> samples = sampleRepository.findByName("DDORI");
        // Team team = samples.get(0).getTeam();
        Team2 team2 = samples.get(0).getTeam2();
        // String teamName = team.getTeamName();
        String teamName2 = team2.getTeamName();
        // assertThat(teamName, is("개발4팀"));
        assertThat(teamName2, is("개발4팀_1"));
    }

    @Test
    @Transactional
    public void Sample_fetch_Test () {
        /*
        List<Sample> samples = sampleRepository.findByNameFetch();
        Team team = samples.get(0).getTeam();
        String teamName = team.getTeamName();
        assertThat(teamName, is("개발4팀"));
         */
    }
    @Test
    public void Sampel_Team_쿼리DSL_조회 () {
        List<SampleTeamDto> samples = sampleRepository.findBySampleTeam("DDORI");
        assertThat(samples.size(), is(1));
    }

    @Test
    public void Sampel_Image_쿼리DSL_조회 () {
        List<SampleImageDto> samples = sampleRepository.findBySampleImage("DDORI");
        assertThat(samples.size(), is(1));
    }

    @Test
    public void Sampel_ImageEntity통한_Sample_접근_쿼리DSL_조회 () {
        List<SampleImageDto> samples = sampleRepository.findBySampleImageUserIdx();
        assertThat(samples.size(), is(1));
    }

    @Test
    @Transactional
    public void MappedByTest () {
        Image image = imageRepository.findByIdx((long) 2);
        image.setImageName("저장테스트");
        imageRepository.save(image);
        // imageRepository.flush();
    }

    @Test
    @Transactional
    public void MappedByInsertTest () {

        Sample sample = sampleRepository.findByName("SYKKIM").get(0);
        // Sample sample = samples.get(0);

        Image image = new Image();
        // image.setIdx(3L);
         image.setImageUrl("/user/save1");
         image.setImageName("저장 테스트1");
         // image.setSample(sample);
         // imageRepository.save(image);
         sample.addImage(image);
         sampleRepository.saveAndFlush(sample);
    }


    @Test
    @Transactional
    public void Sample_팀_팀레파지토리_저장_Test () {
       Team team = Team.builder()
               .teamCode("0002")
               .teamName("개발5팀")
               .build();
       // https://joont92.github.io/jpa/Spring-Data-JPA/
        // Team insertTeam = teamRepository.save(team);
        entityManager.persist(team);
        entityManager.flush();
        // assertThat(entityManager.contains(insertTeam), is(true));
    }
}
