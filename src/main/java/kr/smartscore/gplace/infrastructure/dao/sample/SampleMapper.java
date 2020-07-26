package kr.smartscore.gplace.infrastructure.dao.sample;
import kr.smartscore.gplace.infrastructure.dao.sample.vo.SampleVo;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SampleMapper {
    public Optional<SampleVo> findById(long id);
}
