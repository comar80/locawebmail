package br.com.fiap.locamail.data.model


data class UserGet(
    var id: String,
    var name: String,
    var lastName: String,
    var username: String,
    var password: String,
    var tema_escuro: Boolean
)
