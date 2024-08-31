package com.example.kopring_study.domain.user

class User(
    val id: Long? = null,
    val name: String? = null,
    val email: String,
    password: String? = null,
    role: UserRoleEnum = UserRoleEnum.USER
) {
    var password: String? = password
        private set

    var role: UserRoleEnum = role
        private set

    fun passwordEncoding(encodingPassword: String) {
        this.password = encodingPassword
    }

    fun changeRole(role: UserRoleEnum) {
        this.role = role
    }
}