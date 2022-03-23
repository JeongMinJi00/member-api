package com.api.member.frm.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionEnum {
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "실행 시 오류가 발생했습니다."),
	NULLPOINTER_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "실행 시 오류가 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
//    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다."),
    
    UNKNOWN_ERROR(HttpStatus.NOT_FOUND, "9999", "요청을 처리할 수 없습니다. 잠시 후 다시 시도해 주세요."),
    REQUIRED_LOGIN(HttpStatus.OK, "9980", "로그인 후 이용해 주세요."),
    REQUIRED_CERTIFICATION(HttpStatus.OK, "9970", "전화번호 인증 후 진행해주세요."),
    ALREADY_EXIST_USERID(HttpStatus.OK, "9960", "이미 존재하는 아이디입니다."),
    NOT_FOUND_MEMBER(HttpStatus.OK, "9950", "일치하는 회원 정보가 없습니다."),
    REQUIRED_CERTIFICATION_NUM(HttpStatus.OK, "9940", "인증번호를 재발급 해주세요."),
    REQUIRED_INPUT_BOX(HttpStatus.OK, "9930", "전화번호 또는 인증번호를 입력해 주세요."),
    REGISTER_MEMBER_ERROR(HttpStatus.OK, "9920", "회원가입 처리시 오류가 발생했습니다."),
    ALREADY_LOGIN(HttpStatus.OK, "9910", "로그아웃 후 이용해주세요."),
    CHANGE_PASSWORD_ERROR(HttpStatus.OK, "9890", "비밀번호 변경 시 오류가 발생했습니다."),
    NOT_VALID_CERTIFICATION(HttpStatus.OK, "9880", "인증번호가 올바르지 않습니다."),
    REQUIRED_NEW_PW(HttpStatus.OK, "9870", "변경할 비밀번호를 입력해주세요.")
    ;

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
