package kr.smartscore.gplace.web;

import kr.smartscore.gplace.domain.sample.SampleRepository;
import kr.smartscore.gplace.domain.sample.TeamRepository;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.domain.sample.entity.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        Team team = samples.get(0).getTeam();
        String teamName = team.getTeamName();
        assertThat(teamName, is("개발4팀"));
    }

    @Test
    @Transactional
    public void Sample_fetch_Test () {
        List<Sample> samples = sampleRepository.findByNameFetch();
        Team team = samples.get(0).getTeam();
        String teamName = team.getTeamName();
        assertThat(teamName, is("개발4팀"));
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
