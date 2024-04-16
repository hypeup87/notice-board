//package com.board.notice.service;
//
//import com.board.notice.entity.NoticeBoard;
//import com.board.notice.model.request.NoticeInsertRequestDTO;
//import com.board.notice.model.request.NoticeModifyRequestDTO;
//import com.board.notice.model.response.NoticeResponseDTO;
//import com.board.notice.repository.NoticeBoardRepository;
//import com.board.notice.repository.jpa.NoticeBoardJPARepository;
//import com.board.notice.repository.jpa.NoticeFileJPARepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Collections;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ActiveProfiles("test")
//class NoticeBoardServiceTest {
//
//    @Mock
//    private FileUploadComponent fileUploadComponent;
//
//    @Mock
//    private NoticeBoardRepository noticeBoardRepository;
//
//    @Mock
//    private NoticeBoardJPARepository noticeBoardJPARepository;
//
//    @Mock
//    private NoticeFileJPARepository noticeFileJPARepository;
//
//    @InjectMocks
//    private NoticeBoardService noticeBoardService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testGetNoticeboardList() {
//        int id = 1;
//        NoticeResponseDTO mockResponse = new NoticeResponseDTO();
//
//        when(noticeBoardRepository.findNoticeData(anyInt())).thenReturn(mockResponse);
//        NoticeResponseDTO responseDTO = noticeBoardService.findNoticeData(id);
//
//        assertNotNull(responseDTO);
//        assertEquals(mockResponse, responseDTO);
//    }
//
//    @Test
//    void testDeleteNoticeBoardData() {
//        int id = 1;
//        long mockDeletedCount = 1L;
//
//        when(noticeBoardRepository.deleteNoticeData(anyInt())).thenReturn(mockDeletedCount);
//        long deletedCount = noticeBoardService.deleteNoticeBoardData(id);
//
//        assertEquals(mockDeletedCount, deletedCount);
//    }
//
//    @Test
//    void testCreateNoticeInfo() {
//        NoticeInsertRequestDTO requestDTO = new NoticeInsertRequestDTO();
//        when(fileUploadComponent.writeFile(any())).thenReturn(Collections.emptyMap());
//        NoticeBoard savedNoticeBoard = new NoticeBoard();
//        savedNoticeBoard.setId(1);
//
//        when(noticeBoardJPARepository.save(any(NoticeBoard.class))).thenReturn(savedNoticeBoard);
//        boolean result = noticeBoardService.createNoticeInfo(requestDTO);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testUpdateNoticeInfo() {
//        int nbId = 1;
//        NoticeModifyRequestDTO requestDTO = new NoticeModifyRequestDTO();
//
//        when(noticeBoardRepository.updateNoticeData(anyInt(), any(NoticeModifyRequestDTO.class))).thenReturn(1L);
//        boolean result = noticeBoardService.updateNoticeInfo(nbId, requestDTO);
//
//        assertTrue(result);
//    }
//}
