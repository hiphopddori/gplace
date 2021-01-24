package kr.smartscore.gplace.domain.sample;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.smartscore.gplace.domain.sample.entity.QImage;
import kr.smartscore.gplace.domain.sample.entity.QSample;
import kr.smartscore.gplace.domain.sample.entity.QTeam2;
import kr.smartscore.gplace.domain.sample.entity.Sample;
import kr.smartscore.gplace.web.dto.sample.SampleImageDto;
import kr.smartscore.gplace.web.dto.sample.SampleTeamDto;
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
    @Override
    public List<Sample> findBySample(String email, String name) {
        return queryFactory.selectFrom(QSample.sample)
                .where(eqEmail(email),
                        eqName(name))
                .fetch();
    }
    @Override
    public List<SampleTeamDto> findBySampleTeam(String name) {
        return queryFactory.select((Projections.fields(SampleTeamDto.class,
                QSample.sample.name,
                QTeam2.team2.teamName)))
                .from(QSample.sample)
                .leftJoin(QSample.sample.team2, QTeam2.team2)
                .where(QSample.sample.name.eq(name))
                .fetch();
    }
    public List<SampleImageDto> findBySampleImage(String name) {
        return queryFactory.select((Projections.fields(SampleImageDto.class,
                QSample.sample.name,
                QImage.image.imageName)))
                .from(QSample.sample)
                .leftJoin(QSample.sample.images, QImage.image)
                .where(QSample.sample.name.eq(name))
                .fetch();
    }

    public List<SampleImageDto> findByImageJsonColumn() {
        return queryFactory.select((Projections.fields(SampleImageDto.class,
                QImage.image.metaInfo.as("metaInfo"),
                QImage.image.imageName)))
                .from(QImage.image)
                .fetch();
    }

    public List<SampleImageDto> findBySampleImageUserIdx() {
        /*
        return queryFactory.select((Projections.fields(SampleImageDto.class,
                QImage.image.sample.id,
                QImage.image.imageName)))
                .from(QImage.image)
                .fetch();
         */
        return null;
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
