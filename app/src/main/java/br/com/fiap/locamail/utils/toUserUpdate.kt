package br.com.fiap.locamail.utils

import br.com.fiap.locamail.data.model.UserGet
import br.com.fiap.locamail.data.model.UserUpdate

fun UserGet.toUserUpdate(): UserUpdate {
    return UserUpdate(
        id = this.id,
        userName = this.username,
        tema_escuro = this.tema_escuro
    )
}
