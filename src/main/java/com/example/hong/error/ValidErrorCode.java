package com.example.hong.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidErrorCode {

    EMPTY_PARAMETER(400, "필수 값이 누락되었습니다."),
    MIN_VALUE(400, "최소값보다 커야 합니다.")
    ;


    private int status;
    private String errorCode;
}
