package br.com.fiap.locamail.model

data class EmailModel(
    val emailId: Int? = null,
    val nome: String? = null,
    val horario: String? = null,
    val titulo: String? = null,
    val previa: String? = null,
    val foto: String? = null
)