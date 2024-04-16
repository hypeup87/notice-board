package com.board.notice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class CommonResponse {
    public ResponseEntity resSuccess(Object contents) {
        return ResponseEntity.ok(
                ResSuccessPattern.builder()
                        .data(contents)
                        .success(true)
                        .build()
        );
    }

    public ResponseEntity resFail(HttpStatus status, String errMsg) {
        return ResponseEntity
                .status(status)
                .body(
                        ResFailPattern.builder()
                                .success(false)
                                .message(errMsg)
                                .build()
                );
    }

    @Builder
    @Getter
    public static class ResFailPattern {
        boolean success;
        String message;
        Object data;
    }

    @Builder
    @Getter
    public static class ResSuccessPattern {
        boolean success;
        Object data;
    }
}
