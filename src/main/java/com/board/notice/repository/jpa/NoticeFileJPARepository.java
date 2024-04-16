package com.board.notice.repository.jpa;

import com.board.notice.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileJPARepository extends JpaRepository<NoticeFile, Integer> {

}