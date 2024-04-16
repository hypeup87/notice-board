package com.board.notice.repository.jpa;

import com.board.notice.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardJPARepository extends JpaRepository<NoticeBoard, Integer> {

}