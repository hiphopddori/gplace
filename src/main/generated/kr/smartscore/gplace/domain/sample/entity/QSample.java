package kr.smartscore.gplace.domain.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = 897230155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSample sample = new QSample("sample");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath picture = createString("picture");

    public final StringPath role = createString("role");

    public final QTeam team;

    public QSample(String variable) {
        this(Sample.class, forVariable(variable), INITS);
    }

    public QSample(Path<? extends Sample> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSample(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSample(PathMetadata metadata, PathInits inits) {
        this(Sample.class, metadata, inits);
    }

    public QSample(Class<? extends Sample> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

