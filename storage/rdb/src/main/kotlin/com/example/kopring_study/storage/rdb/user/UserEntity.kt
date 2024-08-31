package com.example.kopring_study.storage.rdb.user

import com.example.kopring_study.domain.user.User
import com.example.kopring_study.domain.user.UserRoleEnum
import com.example.kopring_study.storage.rdb.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
internal class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    email: String,
    password: String,
    role: UserRoleEnum
): BaseEntity() {
    var name: String = name
        protected set
    @Column(unique = true, nullable = false)
    var email: String = email
        protected set
    @Column(nullable = false)
    var password: String = password
        protected set
    @Enumerated(EnumType.STRING)
    var role: UserRoleEnum = role
        protected set

    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            password = password,
            role = role
        )
    }
}