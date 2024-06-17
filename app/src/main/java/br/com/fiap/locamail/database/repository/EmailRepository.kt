package br.com.fiap.locamail.database.repository

import android.content.Context
import br.com.fiap.locamail.database.dao.CadastroDb
import br.com.fiap.locamail.model.Email

class EmailRepository(context: Context) {

    var db = CadastroDb.getDatabase(context).emailDao()

    fun salvar(email: Email): Long{
        return db.salvar(email = email)
    }

    fun moverEmail(caixaEmailId: Long, emailId: Long): Void{
        return db.moverEmail(caixaEmailId, emailId)
    }

    suspend fun buscarEmail(query: String): List<Email> {
        return db.buscarEmail(query)
    }

    fun buscarTodosEmails(): List<Email> {
        return db.buscarTodosEmails()
    }
}