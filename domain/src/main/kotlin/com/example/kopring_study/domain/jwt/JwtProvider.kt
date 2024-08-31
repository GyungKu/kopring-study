package com.example.kopring_study.domain.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Exception
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
        val removePrefixToken = token.removePrefix(BEARER_PREFIX)
        val jwt = validateToken(removePrefixToken)
        return jwt.subject.toLong()
    }

    private fun validateToken(token: String): DecodedJWT {
        return try {
            JWT.require(algorithm).build().verify(token)
        } catch (e: TokenExpiredException) {
            throw RuntimeException("토큰이 만료되었습니다.")
        } catch (e: SignatureVerificationException) {
            throw RuntimeException("유효하지 않은 토큰 서명입니다.")
        } catch (e: AlgorithmMismatchException) {
            throw RuntimeException("토큰 알고리즘이 일치하지 않습니다.")
        } catch (e: JWTDecodeException) {
            throw RuntimeException("잘못된 JWT 형식입니다.")
        } catch (e: Exception) {
            throw RuntimeException("토큰 검증에 실패했습니다.")
        }
    }

}