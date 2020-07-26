package kr.smartscore.gplace.domain.sample;

import com.querydsl.core.types.dsl.BooleanExpression;
import kr.smartscore.gplace.domain.sample.entity.QSample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Sample> findByEmail(String email) {
        return queryFactory.selectFrom(QSample.sample)
                .where(QSample.sample.email.eq(email))
                .fetch();
    }
    public List<Sample> findBySample(String email, String name) {
        return queryFactory.selectFrom(QSample.sample)
                .where(eqEmail(email),
                        eqName(name))
                .fetch();
    }
    private BooleanExpression eqEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return QSample.sample.email.eq(email);
    }
    private BooleanExpression eqName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return QSample.sample.name.eq(name);
    }
}
