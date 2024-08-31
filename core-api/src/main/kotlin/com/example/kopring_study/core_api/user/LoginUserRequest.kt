package com.example.kopring_study.core_api.user

import com.example.kopring_study.domain.user.User

class LoginUserRequest(
    val email: String,
    val password: String
) {

    fun toDomain(): User {
        return User(
            email = email,
            password = password
        )
    }

}
