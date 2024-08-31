package com.example.kopring_study.core_api.filter

import com.example.kopring_study.domain.jwt.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class AuthorizationFilter(
    private val jwtProvider: JwtProvider
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        if (token != null) {
            val userId = jwtProvider.getUserIdFromToken(token)
            setAuthenticate(userId)
        }
        filterChain.doFilter(request, response)
    }

    private fun setAuthenticate(userId: Long) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = createAuthentication(userId)
        SecurityContextHolder.setContext(context)
    }

    // 나중에 ROLE 추가하면 UserService 에서 회원정보 구해와서 ROLE 넣어줄 것
    private fun createAuthentication(userId: Long): Authentication {
        val authorities = listOf<GrantedAuthority>()
        return UsernamePasswordAuthenticationToken(userId, null, authorities)
    }
}