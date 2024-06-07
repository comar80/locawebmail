package br.com.fiap.locamail.presentation

sealed class LoginFormEvent {
    data class UserChanged(val user: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()

    object Submit: LoginFormEvent()
}
