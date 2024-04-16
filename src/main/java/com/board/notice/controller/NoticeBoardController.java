package com.board.notice.controller;

import com.board.notice.model.request.NoticeInsertRequestDTO;
import com.board.notice.model.request.NoticeModifyRequestDTO;
import com.board.notice.model.response.CommonResponse;
import com.board.notice.model.response.NoticeResponseDTO;
import com.board.notice.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
@Slf4j
public class NoticeBoardController extends CommonResponse {
    private final NoticeBoardService noticeBoardService;

    @GetMapping("/{id}")
    public ResponseEntity getNoticeInfo(
            @PathVariable int id
    ) {
        NoticeResponseDTO noticeData = noticeBoardService.findNoticeData(id);

        if(ObjectUtils.isNotEmpty(noticeData))
            return this.resSuccess(noticeData);
        else
            return this.resFail(HttpStatus.NO_CONTENT, "data not found");
    }

    @PostMapping
    public ResponseEntity createNoticeInfo(
            @ModelAttribute NoticeInsertRequestDTO noticeInsDTO
    ) {
        log.info("[NoticeBoardController] method: addNoticeInfo(), parameter: {}", noticeInsDTO);

        if (noticeBoardService.createNoticeInfo(noticeInsDTO))
            return this.resSuccess("create success");
        else
            return this.resFail(HttpStatus.BAD_REQUEST, "create fail");
    }

    @PatchMapping("/{id}")
    public ResponseEntity modNoticeInfo(
            @PathVariable int id,
            @ModelAttribute NoticeModifyRequestDTO noticeModDTO
    ) {
        log.info("[NoticeBoardController] method: modNoticeInfo(), parameter: {}, {}", id, noticeModDTO);
        boolean isUpd = noticeBoardService.updateNoticeInfo(id, noticeModDTO);
        if (isUpd)
            return this.resSuccess("update success");
        else
            return this.resFail(HttpStatus.BAD_REQUEST, "update data not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delNoticeInfo(
            @PathVariable int id
    ) {
        log.info("[NoticeBoardController] method: delNoticeInfo(), parameter: {}", id);
        long delCnt = noticeBoardService.deleteNoticeBoardData(id);
        if (delCnt > 0)
            return this.resSuccess("delete success");
        else
            return this.resFail(HttpStatus.BAD_REQUEST, "delete data not found");
    }

}
