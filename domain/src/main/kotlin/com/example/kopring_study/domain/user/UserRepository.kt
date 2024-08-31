package com.example.kopring_study.domain.user

interface UserRepository {
    fun create(user: User): Long
}