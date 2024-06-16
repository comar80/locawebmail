package br.com.fiap.locamail.presentation

data class RegistrationSearchState(
    val search: String = "",
    val results: List<String> = emptyList()
)