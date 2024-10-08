package com.pinu.familing.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    // 사용자 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    FAMILY_NOT_FOUND(HttpStatus.NOT_FOUND, "가족 그룹이 존재하지 않습니다."),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    USER_NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED, "사용자가 인증되지 않았습니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "JWT 토큰이 쿠키에 존재하지 않습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다."),
    S3_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "S3에서 이미지를 찾을 수 없습니다."),

    // 잘못된 접근
    BAD_APPROACH(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."),
    CHATROOM_ALREADY_EXISTS(HttpStatus.CONFLICT, "채팅방 생성시 중복된 코드를 사용할 수 없습니다."),

    //가족 코드 생성중
    FAILED_CODE_GENERATION(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."),
    ALREADY_HAVE_FAMILY(HttpStatus.BAD_REQUEST, "가족이 있습니다"),
    INVALID_CODE(HttpStatus.BAD_REQUEST, "코드가 유효하지않습니다."),

    //구독 관련
    ERROR_SUBSCRIPTION_LIST(HttpStatus.BAD_REQUEST, "구독 목록 조회중 에러가 발생했습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
