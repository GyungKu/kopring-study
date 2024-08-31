package com.example.kopring_study.domain.user

enum class UserRoleEnum(val authority: String) {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN")
}