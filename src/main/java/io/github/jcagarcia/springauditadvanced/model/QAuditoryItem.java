package io.github.jcagarcia.springauditadvanced.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuditoryItem is a Querydsl query type for AuditoryItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuditoryItem extends EntityPathBase<AuditoryItem> {

    private static final long serialVersionUID = 1248748110L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuditoryItem auditoryItem = new QAuditoryItem("auditoryItem");

    public final QAuditory auditory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath newData = createString("newData");

    public final StringPath oldData = createString("oldData");

    public final EnumPath<OperationType> operationType = createEnum("operationType", OperationType.class);

    public QAuditoryItem(String variable) {
        this(AuditoryItem.class, forVariable(variable), INITS);
    }

    public QAuditoryItem(Path<? extends AuditoryItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuditoryItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuditoryItem(PathMetadata metadata, PathInits inits) {
        this(AuditoryItem.class, metadata, inits);
    }

    public QAuditoryItem(Class<? extends AuditoryItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auditory = inits.isInitialized("auditory") ? new QAuditory(forProperty("auditory")) : null;
    }

}

