package com.example.kopring_study.domain.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider (@Value("\${jwt.secret-key}") private val secretKey: String) {

    private val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    companion object {
        const val EXPIRATION_DATE: Long = 60 * 60 * 1000L
        const val BEARER_PREFIX = "Bearer "
    }

    // 유저 ROLE 을 추가하게 되면 claim 을 추가할 것
    fun createToken(userId: String): String {
        val date = Date()
        return BEARER_PREFIX + JWT.create()
            .withSubject(userId)
            .withIssuedAt(date)
            .withExpiresAt(Date(date.time + EXPIRATION_DATE))
            .sign(algorithm)
    }

    fun getUserIdFromToken(token: String): Long {
        val jwt = JWT.require(algorithm).build().verify(token)
        return jwt.subject.toLong()
    }

}