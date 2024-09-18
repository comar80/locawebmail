package br.com.fiap.locamail.data.model

data class UserCreate(
    var name: String = "",
    var lastName: String = "",
    var userName: String = "",
    var password: String = ""
)
