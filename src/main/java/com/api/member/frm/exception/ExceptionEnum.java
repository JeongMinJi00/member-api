package com.api.member.frm.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionEnum {
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "���� �� ������ �߻��߽��ϴ�."),
	NULLPOINTER_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "���� �� ������ �߻��߽��ϴ�."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
//    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "������ �����ϴ�."),
    
    UNKNOWN_ERROR(HttpStatus.NOT_FOUND, "9999", "��û�� ó���� �� �����ϴ�. ��� �� �ٽ� �õ��� �ּ���."),
    REQUIRED_LOGIN(HttpStatus.OK, "9980", "�α��� �� �̿��� �ּ���."),
    REQUIRED_CERTIFICATION(HttpStatus.OK, "9970", "��ȭ��ȣ ���� �� �������ּ���."),
    ALREADY_EXIST_USERID(HttpStatus.OK, "9960", "�̹� �����ϴ� ���̵��Դϴ�."),
    NOT_FOUND_MEMBER(HttpStatus.OK, "9950", "��ġ�ϴ� ȸ�� ������ �����ϴ�."),
    REQUIRED_CERTIFICATION_NUM(HttpStatus.OK, "9940", "������ȣ�� ��߱� ���ּ���."),
    REQUIRED_INPUT_BOX(HttpStatus.OK, "9930", "��ȭ��ȣ �Ǵ� ������ȣ�� �Է��� �ּ���."),
    REGISTER_MEMBER_ERROR(HttpStatus.OK, "9920", "ȸ������ ó���� ������ �߻��߽��ϴ�."),
    ALREADY_LOGIN(HttpStatus.OK, "9910", "�α׾ƿ� �� �̿����ּ���."),
    CHANGE_PASSWORD_ERROR(HttpStatus.OK, "9890", "��й�ȣ ���� �� ������ �߻��߽��ϴ�."),
    NOT_VALID_CERTIFICATION(HttpStatus.OK, "9880", "������ȣ�� �ùٸ��� �ʽ��ϴ�."),
    REQUIRED_NEW_PW(HttpStatus.OK, "9870", "������ ��й�ȣ�� �Է����ּ���.")
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
