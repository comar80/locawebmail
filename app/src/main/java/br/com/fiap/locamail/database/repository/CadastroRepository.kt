package br.com.fiap.locamail.database.repository

import android.content.Context
import br.com.fiap.locamail.database.dao.CadastroDb
import br.com.fiap.locamail.model.Cadastro

class CadastroRepository(context: Context) {

    var db = CadastroDb.getDatabase(context).cadastroDao()

    fun salvar(cadastro: Cadastro): Long{
        return db.salvar(cadastro = cadastro)
    }

    fun buscarUsuario(user: String): Cadastro {
        return db.buscarUsuario(user)
    }
}