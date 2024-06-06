package br.com.fiap.locamail.validations

class ValidateSobrenome {

    fun execute(sobrenome: String): ValidationResult {
        if(sobrenome.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "O sobrenome não pode estar em branco"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}