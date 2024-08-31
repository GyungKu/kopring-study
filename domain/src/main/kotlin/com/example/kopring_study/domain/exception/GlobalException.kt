package com.example.kopring_study.domain.exception

class GlobalException(
    message: String,
    val status: Int
): RuntimeException(message) {
}