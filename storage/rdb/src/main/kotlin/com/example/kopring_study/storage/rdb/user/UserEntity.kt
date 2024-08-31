package com.example.kopring_study.storage.rdb.user

import com.example.kopring_study.domain.user.User
import com.example.kopring_study.storage.rdb.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
internal class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    email: String,
    password: String
): BaseEntity() {
    var name = name
        protected set
    var email = email
        protected set
    var password = password
        protected set

    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            password = password
        )
    }
}