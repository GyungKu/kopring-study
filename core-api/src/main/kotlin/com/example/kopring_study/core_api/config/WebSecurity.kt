package com.example.kopring_study.core_api.config

import com.example.kopring_study.core_api.exception.CustomAuthenticationEntryPoint
import com.example.kopring_study.core_api.filter.AuthorizationFilter
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
    private val authorizationFilter: AuthorizationFilter,
    private val entryPoint: CustomAuthenticationEntryPoint
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .headers { it.frameOptions { f -> f.disable() } }
            .addFilterBefore(authorizationFilter,
                UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { it.authenticationEntryPoint(entryPoint) }

        return http.build()
    }

}