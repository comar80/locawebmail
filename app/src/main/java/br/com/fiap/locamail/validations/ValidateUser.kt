package br.com.fiap.locamail.validations

class ValidateUser {

    fun execute(user: String): ValidationResult {
        if(user.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Escolha um nome de usuário"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}