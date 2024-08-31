package com.example.kopring_study.core_api.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        // 로그인이 안 되어 있어도 403을 내보내기 때문에 로그인이 필요한 서비스에서 비로그인 일 때 401을 반환
        if (request.getHeader("Authorization") == null) {
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            val errorResponse = CustomErrorResponse("로그인이 필요한 서비스입니다.", HttpStatus.UNAUTHORIZED.value())
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.write(objectMapper.writeValueAsString(errorResponse))
            return
        }
    }
}