package com.board.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import jakarta.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeFile is a Querydsl query type for NoticeFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeFile extends EntityPathBase<NoticeFile> {

    private static final long serialVersionUID = -1208040962L;

    public static final QNoticeFile noticeFile = new QNoticeFile("noticeFile");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> insDate = createDateTime("insDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> nbId = createNumber("nbId", Integer.class);

    public final StringPath saveName = createString("saveName");

    public QNoticeFile(String variable) {
        super(NoticeFile.class, forVariable(variable));
    }

    public QNoticeFile(Path<? extends NoticeFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeFile(PathMetadata metadata) {
        super(NoticeFile.class, metadata);
    }

}

