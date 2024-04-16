package com.board.notice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class FileUploadComponent {
    private final String outputPath = "src/main/resources/upload-files/";

    public Map writeFile(MultipartFile uploadFile) {
        String fileName = uploadFile.getOriginalFilename();
        String prefixFileNm = fileName.substring(0, fileName.lastIndexOf('.'));
        String suffixStr = fileName.substring(fileName.lastIndexOf('.'));
        String resultFileName = prefixFileNm + suffixStr;

        try {
            Path path = Paths.get(outputPath + resultFileName).toAbsolutePath();
            //중복된 파일이 있을경우 파일명+시간 으로 파일을 생성한다.
            if (path.toFile().exists()) {
                resultFileName = prefixFileNm + "_" + System.currentTimeMillis() + suffixStr;
            }
            path = Paths.get(outputPath + resultFileName).toAbsolutePath();
            uploadFile.transferTo(path.toFile());

        } catch (IOException e) {
            log.error("error occurred while writing the file", e, e.getMessage(), e.getCause());
            return null;
        }

        Map<String, String> fileInfo = new HashMap<>();
        fileInfo.put("originName", fileName);
        fileInfo.put("saveName", resultFileName);

        return fileInfo;
    }
}
