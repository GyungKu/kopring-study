package com.example.kopring_study.core_api.user

import com.example.kopring_study.domain.user.User
import com.example.kopring_study.domain.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/check-user")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun userCheck(@AuthenticationPrincipal user: User): ResponseEntity<User> {
        return ResponseEntity.ok(user)
    }

    @GetMapping("/check-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun adminCheck(@AuthenticationPrincipal user: User): ResponseEntity<User> {
        return ResponseEntity.ok(user)
    }

}