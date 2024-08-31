package com.example.kopring_study.core_api.config

import com.example.kopring_study.core_api.filter.AuthorizationFilter
import com.example.kopring_study.domain.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class WebSecurity(
    val jwtProvider: JwtProvider
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { c -> c.disable() }
            .headers { h -> h.frameOptions { f -> f.disable() } }
            .addFilterBefore(AuthorizationFilter(jwtProvider = jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}