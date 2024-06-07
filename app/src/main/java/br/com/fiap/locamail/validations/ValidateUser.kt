package br.com.fiap.locamail.validations

class ValidateUser {

    fun execute(user: String): ValidationResult {
        if(user.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Preencha com o nome de usuário"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}