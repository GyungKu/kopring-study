package com.example.kopring_study.domain.exception

class GlobalException(
    val error: ErrorEnum
): RuntimeException() {
}