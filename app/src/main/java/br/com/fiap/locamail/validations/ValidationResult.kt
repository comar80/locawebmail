package br.com.fiap.locamail.validations

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
