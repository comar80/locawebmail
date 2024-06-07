package br.com.fiap.locamail.presentation

data class RegistrationFormState(
    val nome: String = "",
    val nomeError: String? = null,
    val sobrenome: String = "",
    val sobrenomeError: String? = null,
    val user: String = "",
    val userError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
)