package com.study.springBoot.basic.day4.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //ErrorCode for Join
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST,"이미 존재하는 아이디입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
