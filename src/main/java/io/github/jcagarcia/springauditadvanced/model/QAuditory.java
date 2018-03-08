package io.github.jcagarcia.springauditadvanced.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuditory is a Querydsl query type for Auditory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuditory extends EntityPathBase<Auditory> {

    private static final long serialVersionUID = 1411332251L;

    public static final QAuditory auditory = new QAuditory("auditory");

    public final SetPath<AuditoryItem, QAuditoryItem> auditoryItems = this.<AuditoryItem, QAuditoryItem>createSet("auditoryItems", AuditoryItem.class, QAuditoryItem.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final StringPath feature = createString("feature");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath module = createString("module");

    public final EnumPath<OperationResult> operationResult = createEnum("operationResult", OperationResult.class);

    public final EnumPath<OperationType> operationType = createEnum("operationType", OperationType.class);

    public final StringPath step = createString("step");

    public final StringPath url = createString("url");

    public final StringPath userName = createString("userName");

    public QAuditory(String variable) {
        super(Auditory.class, forVariable(variable));
    }

    public QAuditory(Path<? extends Auditory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditory(PathMetadata metadata) {
        super(Auditory.class, metadata);
    }

}

