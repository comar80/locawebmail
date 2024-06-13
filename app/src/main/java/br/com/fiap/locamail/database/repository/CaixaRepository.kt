package br.com.fiap.locamail.database.repository

import android.content.Context
import br.com.fiap.locamail.database.dao.CadastroDb
import br.com.fiap.locamail.model.CaixaComEmails
import br.com.fiap.locamail.model.CaixaEmail

class CaixaRepository(context: Context) {

    var db = CadastroDb.getDatabase(context).caixaDao()

    fun salvar(caixa: CaixaEmail): Long{
        return db.salvar(caixa = caixa)
    }

    fun getCaixaComEmails(): List<CaixaComEmails> {
        return db.getCaixaComEmails()
    }

    fun getUmaCaixaComEmails(caixaId: Long): CaixaComEmails {
        return db.getUmaCaixaComEmails(caixaId)
    }
}