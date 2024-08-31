package com.example.kopring_study.core_api.user

import com.example.kopring_study.domain.user.User
import com.example.kopring_study.domain.user.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody signupUserRequest: SignupUserRequest): ResponseEntity<Long> {
        val user = User(
            name = signupUserRequest.name,
            email = signupUserRequest.email,
            password = signupUserRequest.password
        )
        val id = userService.create(user)
        return ResponseEntity.ok(id)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUserRequest: LoginUserRequest): ResponseEntity<Unit> {
        val token = userService.login(loginUserRequest.toDomain())
        return ResponseEntity.ok().header("Authorization", token).build()
    }

    @GetMapping("/check")
    fun check(@AuthenticationPrincipal userId: Long): ResponseEntity<Long> {
        return ResponseEntity.ok(userId)
    }

}