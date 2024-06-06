package br.com.fiap.locamail.validations

class ValidateNome {

    fun execute(nome: String): ValidationResult {
        if(nome.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "O nome não pode estar em branco"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}