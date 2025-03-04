package com.example.kopring_study.domain.user

interface UserRepository {
    fun create(user: User): Long
    fun findByEmailOrNull(email: String): User?
    fun findByIdOrNull(id: Long): User?
}