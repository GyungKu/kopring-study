package com.example.kopring_study.domain.user

import com.example.kopring_study.domain.exception.GlobalException
import com.example.kopring_study.domain.jwt.JwtProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAppender: UserAppender,
    private val userReader: UserReader,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) {
    fun create(user: User): Long {
        user.passwordEncoding(passwordEncoder.encode(user.password))
        return userAppender.create(user)
    }

    fun login(user: User): String {
        val findUser = userReader.getByEmail(user.email)
        if (!passwordEncoder.matches(user.password, findUser.password)) {
            throw GlobalException("일치하지 않는 비밀번호", 400)
        }
        return jwtProvider.createToken(findUser.id.toString())
    }

    fun getById(id: Long): User {
        return userReader.getById(id)
    }
}