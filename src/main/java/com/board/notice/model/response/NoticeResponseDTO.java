package com.board.notice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDTO {
    private int id;
    private String title;
    private String contents;
    private LocalDateTime insertDate;
    private String manager;
    private int viewCnt;
}
