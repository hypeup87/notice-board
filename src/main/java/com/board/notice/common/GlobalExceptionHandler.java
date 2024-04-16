package com.board.notice.common;

import com.board.notice.model.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * controller 공통 exception 처리
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends CommonResponse {
    /**
     * @param e exception
     * @return response entity
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handle(Exception e) {
        e.printStackTrace();
        log.error("Exception - message:{}", e.getMessage(), e);
        return this.resFail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}



