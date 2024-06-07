package br.com.fiap.locamail.validations

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if(password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Campo em branco. Preencha com sua senha"
            )
        }
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "A senha tem que ter mais de 8 caracteres entre números e letras"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "A senha tem que ter números e letras"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}