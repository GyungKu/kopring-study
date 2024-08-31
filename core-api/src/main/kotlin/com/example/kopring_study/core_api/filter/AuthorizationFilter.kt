package com.example.kopring_study.core_api.filter

import com.example.kopring_study.core_api.exception.CustomErrorResponse
import com.example.kopring_study.domain.exception.GlobalException
import com.example.kopring_study.domain.jwt.JwtProvider
import com.example.kopring_study.domain.user.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthorizationFilter(
    private val jwtProvider: JwtProvider,
    private val userService: UserService,
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        if (token != null) {
            // 토큰에 문제가 있거나, 조회 시 존재하지 않는 ID 일 때 예외 처리
            try {
                val userId = jwtProvider.getUserIdFromToken(token)
                setAuthenticate(userId)
            } catch (e: GlobalException) {
                val errorResponse = CustomErrorResponse(e.error.message, e.error.status)
                response.characterEncoding = "UTF-8"
                response.status = e.error.status
                response.contentType = "application/json"
                response.writer.write(objectMapper.writeValueAsString(errorResponse))
                return
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun setAuthenticate(userId: Long) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = createAuthentication(userId)
        SecurityContextHolder.setContext(context)
    }

    private fun createAuthentication(userId: Long): Authentication {
        val authorities = mutableListOf<GrantedAuthority>()
        val user = userService.getById(userId)
        authorities.add(SimpleGrantedAuthority(user.role.authority))
        return UsernamePasswordAuthenticationToken(user, null, authorities)
    }
}