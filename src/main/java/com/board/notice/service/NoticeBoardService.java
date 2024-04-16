package com.board.notice.service;

import com.board.notice.entity.NoticeBoard;
import com.board.notice.entity.NoticeFile;
import com.board.notice.model.request.NoticeInsertRequestDTO;
import com.board.notice.model.request.NoticeModifyRequestDTO;
import com.board.notice.model.response.NoticeResponseDTO;
import com.board.notice.repository.jpa.NoticeBoardJPARepository;
import com.board.notice.repository.NoticeBoardRepository;
import com.board.notice.repository.jpa.NoticeFileJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeBoardService {
    private final FileUploadComponent fileUploadComponent;
    private final NoticeBoardRepository noticeBoardRepository;
    private final NoticeBoardJPARepository noticeBoardJPARepository;
    private final NoticeFileJPARepository noticeFileJPARepository;

    /**
     * 공지사항 정보 조회
     * @param id
     * @return
     */
    public NoticeResponseDTO findNoticeData(int id) {
        NoticeResponseDTO noticeResponseDTO = noticeBoardRepository.findNoticeData(id);
        // 비동기 조회수 증가
        noticeBoardRepository.noticeAddCountData(id);
        return noticeResponseDTO;
    }

    /**
     * 공지사항 정보 삭제
     * @param id
     * @return
     */
    public Long deleteNoticeBoardData(int id) {
        return noticeBoardRepository.deleteNoticeData(id);
    }

    /**
     * 공지사항 정보 등록
     * @param noticeInsDTO
     * @return
     */
    @Transactional
    public boolean createNoticeInfo(NoticeInsertRequestDTO noticeInsDTO) {
        NoticeBoard noticeBoard = NoticeBoard.builder()
                .title(noticeInsDTO.getTitle())
                .contents(noticeInsDTO.getContents())
                .startDate(noticeInsDTO.getStartDate())
                .endDate(noticeInsDTO.getEndDate())
                .build();
        noticeBoardJPARepository.save(noticeBoard);

        if(!ObjectUtils.isEmpty(noticeInsDTO.getFiles())) {
            List<Map> fileNameList = writeNoticeFile(noticeInsDTO.getFiles());
            insertNoticeFileData(noticeBoard.getId(), fileNameList);
        }

        return true;
    }

    /**
     * 공지사항 정보 수정
     * @param nbId
     * @param noticeUpdDTO
     * @return
     */
    @Transactional
    public boolean updateNoticeInfo(int nbId, NoticeModifyRequestDTO noticeUpdDTO) {
        boolean result = false;
        if(!ObjectUtils.isEmpty(noticeUpdDTO.getFiles())) {
            List<Map> fileNameList = writeNoticeFile(noticeUpdDTO.getFiles());
            noticeBoardRepository.deleteFileByNbId(nbId);
            insertNoticeFileData(nbId, fileNameList);
            result = true;
        }

        long updCnt = noticeBoardRepository.updateNoticeData(nbId, noticeUpdDTO);
        if(updCnt > 0)
            result = true;

        return result;
    }

    /**
     * 업로드 시도한 파일리스트 'NoticeFile' 데이터 저장
     * @param nbId
     * @param fileNameList
     * @return
     */
    private boolean insertNoticeFileData(int nbId, List<Map> fileNameList) {
        List<NoticeFile> noticeFiles = fileNameList.stream()
                .map(nameMap -> NoticeFile.builder()
                        .fileName((String) nameMap.get("originName"))
                        .saveName((String) nameMap.get("saveName"))
                        .nbId(nbId)
                        .build())
                .collect(Collectors.toList());
        noticeFileJPARepository.saveAll(noticeFiles);
        return true;
    }

    /**
     * 파일 업로드
     * @param files
     * @return
     */
    private List<Map> writeNoticeFile(List<MultipartFile> files) {
        return files.stream()
                .map(fileUploadComponent::writeFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}