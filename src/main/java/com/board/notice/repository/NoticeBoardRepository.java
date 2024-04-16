package com.board.notice.repository;

import com.board.notice.constant.NoticeBoardConfigConst;
import com.board.notice.entity.*;
import com.board.notice.model.request.NoticeModifyRequestDTO;
import com.board.notice.model.response.NoticeResponseDTO;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Log4j2
@Repository
public class NoticeBoardRepository extends QuerydslRepositorySupport {
    public NoticeBoardRepository() {
        super(NoticeBoardRepository.class);
    }
    private JPAQueryFactory queryFactory;
    @Override
    @PersistenceContext(unitName = NoticeBoardConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF)
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
    private QNoticeBoard noticeBoard = QNoticeBoard.noticeBoard;
    private QNoticeFile noticeFile = QNoticeFile.noticeFile;

    /**
     * 공지사항 정보 조회
     * @param id
     * @return
     */
    @Transactional
    public NoticeResponseDTO findNoticeData(int id) {
        return queryFactory
            .select(Projections.constructor(
                    NoticeResponseDTO.class,
                    noticeBoard.id,
                    noticeBoard.title,
                    noticeBoard.contents,
                    noticeBoard.insDate,
                    noticeBoard.insManager,
                    noticeBoard.viewCnt
            )).from(noticeBoard)
            .where(noticeBoard.isDel.eq(false)
                    .and(noticeBoard.startDate.lt(LocalDateTime.now()))
                    .and(noticeBoard.endDate.gt(LocalDateTime.now())
                    .and(noticeBoard.id.eq(id)))
            ).fetchOne();
    }

    /**
     * 공지사항 정보 삭제
     * @param id
     * @return
     */
    @Transactional
    public long deleteNoticeData(int id) {
        return queryFactory
                .update(noticeBoard)
                .set(noticeBoard.isDel, true)
                .set(noticeBoard.modDate, LocalDateTime.now())
                .where(noticeBoard.id.eq(id))
                .execute();
    }

    /**
     * 공지사항 정보 등록
     * @param id
     * @param noticeUpdDTO
     * @return
     */
    @Transactional
    public long updateNoticeData(int id, NoticeModifyRequestDTO noticeUpdDTO) {
        JPAUpdateClause updateBuilder = queryFactory.update(noticeBoard);
        if (!ObjectUtils.isEmpty(noticeUpdDTO.getTitle()))
            updateBuilder.set(noticeBoard.title, noticeUpdDTO.getTitle());

        if (!ObjectUtils.isEmpty(noticeUpdDTO.getContents()))
            updateBuilder.set(noticeBoard.contents, noticeUpdDTO.getContents());

        if (!ObjectUtils.isEmpty(noticeUpdDTO.getStartDate()))
            updateBuilder.set(noticeBoard.startDate, noticeUpdDTO.getStartDate());

        if (!ObjectUtils.isEmpty(noticeUpdDTO.getEndDate()))
            updateBuilder.set(noticeBoard.endDate, noticeUpdDTO.getEndDate());

        if(updateBuilder.isEmpty()) // update할 내용이 없으면 -1 반환
            return -1;

        return updateBuilder
                .where(noticeBoard.id.eq(id)
                        .and(noticeBoard.isDel.eq(false)))
                .execute();
    }

    /**
     * 파일데이터 삭제
     * @param nbId
     * @return
     */
    @Transactional
    public long deleteFileByNbId(int nbId) {
        return queryFactory
                .delete(noticeFile)
                .where(noticeFile.nbId.eq(nbId))
                .execute();
    }

    /**
     * 조회수 증가, 대용량 처리를 위한 비동기처리
     * @param id
     */
    @Async
    @Transactional
    public void noticeAddCountData(int id) {
        queryFactory
            .update(noticeBoard)
                .set(noticeBoard.viewCnt, noticeBoard.viewCnt.add(1))
                .where(noticeBoard.id.eq(id))
                .execute();
    }

}
