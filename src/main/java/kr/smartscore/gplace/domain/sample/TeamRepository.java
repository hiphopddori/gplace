package kr.smartscore.gplace.domain.sample;

import kr.smartscore.gplace.domain.sample.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
