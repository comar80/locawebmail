package br.com.fiap.locamail.validations

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(repeatedPassword.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Campo em branco. Repita a senha"
            )
        }
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "As senhas não são iguais"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}