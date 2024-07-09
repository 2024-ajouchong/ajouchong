package com.ajouchong.Exception;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    _VERIFICATION_CODE_NOT_FOUND("인증번호를 찾을 수 없습니다."),
    _VERIFICATION_CODE_EXPIRED("인증번호가 만료되었습니다.");

    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

}
