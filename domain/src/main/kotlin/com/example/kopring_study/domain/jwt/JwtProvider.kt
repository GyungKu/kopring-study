package com.example.kopring_study.domain.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.kopring_study.domain.exception.ErrorEnum
import com.example.kopring_study.domain.exception.GlobalException
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
            throw GlobalException(ErrorEnum.TOKEN_EXPIRED)
        } catch (e: SignatureVerificationException) {
            throw GlobalException(ErrorEnum.INVALID_SIGNATURE)
        } catch (e: AlgorithmMismatchException) {
            throw GlobalException(ErrorEnum.ALGORITHM_MISMATCH)
        } catch (e: JWTDecodeException) {
            throw GlobalException(ErrorEnum.INVALID_JWT_FORMAT)
        } catch (e: Exception) {
            throw GlobalException(ErrorEnum.TOKEN_VALIDATION_FAILED)
        }
    }

}