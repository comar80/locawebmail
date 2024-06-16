package br.com.fiap.locamail.presentation

sealed class RegistrationSearchEvent {
    data class Search (val search: String) : RegistrationSearchEvent()

}
