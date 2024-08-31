package com.example.kopring_study.domain.exception

enum class ErrorEnum(
    val message: String,
    val status: Int
    ) {

    // USER
    NOT_FOUND_USER_ID("존재하지 않는 유저 ID", 404),
    NOT_FOUND_USER_EMAIL("존재하지 않는 이메일", 404),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.", 400),

    // TOKEN
    TOKEN_EXPIRED("토큰이 만료되었습니다.", 400),
    INVALID_SIGNATURE("토큰 서명이 유효하지 않습니다.", 400),
    ALGORITHM_MISMATCH("토큰의 알고리즘이 일치하지 않습니다.", 400),
    INVALID_JWT_FORMAT("JWT 형식이 올바르지 않습니다.", 400),
    TOKEN_VALIDATION_FAILED("토큰 검증 중 오류가 발생했습니다.", 400)
}