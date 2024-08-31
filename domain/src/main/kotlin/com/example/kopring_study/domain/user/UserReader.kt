package com.example.kopring_study.domain.user

import com.example.kopring_study.domain.exception.GlobalException
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun getByEmail(email: String): User {
        return userRepository.findByEmailOrNull(email)?: throw GlobalException("존재 하지 않는 이메일", 404)
    }

    fun getById(id: Long): User {
        return userRepository.findByIdOrNull(id)?: throw GlobalException("존재 하지 않는 ID", 404)
    }

}