package com.board.notice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notice_board")
@Table
public class NoticeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nb_id")
    private int id;

    @Column(name = "nb_title")
    private String title;

    @Column(name = "nb_contents")
    private String contents;

    @Column(name = "nb_view_cnt")
    private int viewCnt;

    @Column(name = "nb_start_date")
    private LocalDateTime startDate;

    @Column(name = "nb_end_date")
    private LocalDateTime endDate;

    @Column(name = "nb_ins_manager")
    private String insManager;

    @CreatedDate
    @Column(name = "nb_ins_date", updatable = false)
    private LocalDateTime insDate;

    @LastModifiedDate
    @Column(name = "nb_mod_date", insertable = false, nullable = false)
    private LocalDateTime modDate;

    @Column(name = "nb_is_del")
    private boolean isDel;


    @PrePersist
    public void prePersist() {
        this.insManager = "admin";
        this.insDate = LocalDateTime.now();
        this.modDate = LocalDateTime.now();
    }

}
