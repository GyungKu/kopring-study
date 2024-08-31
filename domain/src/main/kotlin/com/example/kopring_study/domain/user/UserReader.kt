package com.example.kopring_study.domain.user

import com.example.kopring_study.domain.exception.ErrorEnum
import com.example.kopring_study.domain.exception.GlobalException
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun getByEmail(email: String): User {
        return userRepository.findByEmailOrNull(email)?: throw GlobalException(ErrorEnum.NOT_FOUND_USER_EMAIL)
    }

    fun getById(id: Long): User {
        return userRepository.findByIdOrNull(id)?: throw GlobalException(ErrorEnum.NOT_FOUND_USER_ID)
    }

}