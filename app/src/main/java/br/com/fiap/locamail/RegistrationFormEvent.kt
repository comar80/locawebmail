package br.com.fiap.locamail

sealed class RegistrationFormEvent {
    data class NomeChanged(val nome: String) : RegistrationFormEvent()
    data class SobrenomeChanged(val sobrenome: String) : RegistrationFormEvent()
    data class UserChanged(val user: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
