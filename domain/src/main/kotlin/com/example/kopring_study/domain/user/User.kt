package com.example.kopring_study.domain.user

class User(
    val id: Long? = null,
    val name: String? = null,
    val email: String,
    password: String? = null
) {
    var password: String? = password
        private set

    fun passwordEncoding(encodingPassword: String) {
        this.password = encodingPassword
    }
}