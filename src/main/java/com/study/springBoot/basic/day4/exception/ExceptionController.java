package com.study.springBoot.basic.day4.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    /**
     * ExceptionHandler 어노테이션은 지정된 클래스에서 발생하는 에러를 잡아서
     * 메소드로 처리해주는 기능을 제공한다.
     */
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ExceptionResponse> customRequestException(final CustomException CE){
        log.warn("Exception: "+CE.getErrorCode());
        return ResponseEntity.badRequest().body(new ExceptionResponse(CE.getMessage(),CE.getErrorCode()));
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse{
        private String msg;
        private ErrorCode errorCode;
    }
}
