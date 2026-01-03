package com.simats.digitaldetox

data class LoginResponse(
    val status: String,
    val message: String,
    val user: User?
)
