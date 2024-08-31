package com.example.kopring_study.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun getByEmail(email: String): User {
        return userRepository.findByEmailOrNull(email)?: throw RuntimeException("존재 하지 않는 이메일")
    }

    fun getById(id: Long): User {
        return userRepository.findByIdOrNull(id)?: throw RuntimeException("존재 하지 않는 ID")
    }

}