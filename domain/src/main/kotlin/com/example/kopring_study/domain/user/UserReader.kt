package com.example.kopring_study.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun getByEmail(email: String): User {
        return userRepository.findByEmailOrNull(email)?: throw RuntimeException("존재 하지 않는 이메일")
    }

}