package com.example.kopring_study.domain.user

import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val userRepository: UserRepository
) {
    fun create(user: User): Long {
        return userRepository.create(user)
    }
}