package com.example.democourse.screens.login

data class LoginState (
    val email:String = "",
    val emailError: String? = null,
    val password:String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
    val errorMessage:String?=null,
    val isLoggedIn: Boolean = false
)