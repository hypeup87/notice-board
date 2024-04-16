package com.board.notice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import jakarta.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeBoard is a Querydsl query type for NoticeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeBoard extends EntityPathBase<NoticeBoard> {

    private static final long serialVersionUID = 1201910436L;

    public static final QNoticeBoard noticeBoard = new QNoticeBoard("noticeBoard");

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> insDate = createDateTime("insDate", java.time.LocalDateTime.class);

    public final StringPath insManager = createString("insManager");

    public final BooleanPath isDel = createBoolean("isDel");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewCnt = createNumber("viewCnt", Integer.class);

    public QNoticeBoard(String variable) {
        super(NoticeBoard.class, forVariable(variable));
    }

    public QNoticeBoard(Path<? extends NoticeBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeBoard(PathMetadata metadata) {
        super(NoticeBoard.class, metadata);
    }

}

