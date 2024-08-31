package com.example.kopring_study.domain.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAppender: UserAppender,
    private val passwordEncoder: PasswordEncoder
) {
    fun create(user: User): Long {
        user.passwordEncoding(passwordEncoder.encode(user.password))
        return userAppender.create(user)
    }
}