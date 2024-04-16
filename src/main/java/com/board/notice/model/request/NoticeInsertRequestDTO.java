package com.board.notice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeInsertRequestDTO {
    private String title;
    private String contents;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<MultipartFile> files;
}
