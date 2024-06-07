package br.com.fiap.locamail.presentation

data class LoginFormState(
    val user: String = "",
    val userError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)