package com.board.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notice_file")
@Table
public class NoticeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nf_id")
    private int id;

    @Column(name = "nb_id")
    private int nbId;

    @Column(name = "nf_file_name")
    private String fileName;

    @Column(name = "nf_save_name")
    private String saveName;

    @CreatedDate
    @Column(name = "nf_ins_date", updatable = false)
    private LocalDateTime insDate;

    @PrePersist
    public void prePersist() {
        this.insDate = LocalDateTime.now();
    }

}
