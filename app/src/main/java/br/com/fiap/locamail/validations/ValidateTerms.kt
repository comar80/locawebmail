package br.com.fiap.locamail.validations

class ValidateTerms {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if(!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}