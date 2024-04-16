package com.board.notice.controller;

import com.board.notice.model.request.NoticeInsertRequestDTO;
import com.board.notice.model.request.NoticeModifyRequestDTO;
import com.board.notice.model.response.NoticeResponseDTO;
import com.board.notice.service.NoticeBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class NoticeBoardControllerTest {
    @Mock
    private NoticeBoardService noticeBoardService;

    @InjectMocks
    private NoticeBoardController noticeBoardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetNoticeInfo_Success() {
        int id = 34;
        NoticeResponseDTO mockResponse = new NoticeResponseDTO(); // Create a mock response
        when(noticeBoardService.findNoticeData(anyInt())).thenReturn(mockResponse);
        ResponseEntity responseEntity = noticeBoardController.getNoticeInfo(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetNoticeInfo_NotFound() {
        int id = 1;
        when(noticeBoardService.findNoticeData(anyInt())).thenReturn(null);
        ResponseEntity responseEntity = noticeBoardController.getNoticeInfo(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testCreateNoticeInfo_Success() {
        NoticeInsertRequestDTO requestDTO = NoticeInsertRequestDTO.builder()
                .title("test title")
                .contents("test contents")
                .startDate(LocalDateTime.parse("2024-04-01T00:00"))
                .startDate(LocalDateTime.parse("2024-08-01T00:00"))
                .files(null)
                .build();
        when(noticeBoardService.createNoticeInfo(requestDTO)).thenReturn(true);
        ResponseEntity responseEntity = noticeBoardController.createNoticeInfo(requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testCreateNoticeInfo_Fail() {
        NoticeInsertRequestDTO requestDTO = new NoticeInsertRequestDTO();
        when(noticeBoardService.createNoticeInfo(requestDTO)).thenReturn(false);
        ResponseEntity responseEntity = noticeBoardController.createNoticeInfo(requestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testModNoticeInfo_Success() {
        int id = 1;
        NoticeModifyRequestDTO requestDTO = NoticeModifyRequestDTO.builder()
                .title("update test title")
                .contents("update test contents")
                .startDate(LocalDateTime.parse("2024-04-01T00:00"))
                .startDate(LocalDateTime.parse("2024-08-01T00:00"))
                .files(null)
                .build();
        when(noticeBoardService.updateNoticeInfo(id, requestDTO)).thenReturn(true);
        ResponseEntity responseEntity = noticeBoardController.modNoticeInfo(id, requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testModNoticeInfo_Fail() {
        int id = 1;
        NoticeModifyRequestDTO requestDTO = new NoticeModifyRequestDTO();
        when(noticeBoardService.updateNoticeInfo(anyInt(), any())).thenReturn(false);
        ResponseEntity responseEntity = noticeBoardController.modNoticeInfo(id, requestDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelNoticeInfo_Success() {
        int id = 1;
        when(noticeBoardService.deleteNoticeBoardData(anyInt())).thenReturn(1L);
        ResponseEntity responseEntity = noticeBoardController.delNoticeInfo(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDelNoticeInfo_Fail() {
        int id = 1;
        when(noticeBoardService.deleteNoticeBoardData(anyInt())).thenReturn(0L);
        ResponseEntity responseEntity = noticeBoardController.delNoticeInfo(id);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}