package com.example.kopring_study.storage.rdb.user

import com.example.kopring_study.domain.user.User
import com.example.kopring_study.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
internal class UserEntityRepository(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun create(user: User): Long {
        val userEntity = userJpaRepository.save(
            UserEntity(
                id = user.id,
                name = user.name!!,
                email = user.email,
                password = user.password!!,
                role = user.role
            )
        )
        return userEntity.id!!
    }

    override fun findByEmailOrNull(email: String): User? {
        return userJpaRepository.findByEmail(email)?.toDomain()
    }

    override fun findByIdOrNull(id: Long): User? {
        return userJpaRepository.findByIdOrNull(id)?.toDomain()
    }

}