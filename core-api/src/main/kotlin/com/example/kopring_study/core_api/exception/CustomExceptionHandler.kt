package com.example.kopring_study.core_api.exception

import com.example.kopring_study.domain.exception.GlobalException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(GlobalException::class)
    fun globalExceptionHandle(e: GlobalException): ResponseEntity<CustomErrorResponse> {
        val response = CustomErrorResponse(e.error.message, e.error.status)
        return ResponseEntity.status(e.error.status).body(response)
    }

}