package br.com.fiap.locamail.database.repository

import android.content.Context
import br.com.fiap.locamail.database.dao.CadastroDb
import br.com.fiap.locamail.model.CaixaComEmails
import br.com.fiap.locamail.model.Email

class EmailRepository(context: Context) {

    var db = CadastroDb.getDatabase(context).emailDao()

    fun salvar(email: Email): Long{
        return db.salvar(email = email)
    }

    fun moverEmail(caixaEmailId: Long, emailId: Long): Long{
        return db.moverEmail(caixaEmailId, emailId)
    }
}