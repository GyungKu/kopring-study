package com.example.kopring_study.storage.rdb.user

import org.springframework.data.jpa.repository.JpaRepository

internal interface UserJpaRepository: JpaRepository<UserEntity, Long> {
}