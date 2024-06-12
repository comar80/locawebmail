package br.com.fiap.locamail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.fiap.locamail.model.CaixaComEmails
import br.com.fiap.locamail.model.Email

@Dao
interface EmailDao {

    @Insert
    fun salvar(email: Email): Long

    @Transaction
    @Query("SELECT * FROM tbl_caixa")
    fun getCaixaComEmails(): List<CaixaComEmails>

    @Transaction
    @Query("SELECT * FROM tbl_caixa WHERE caixaId = :caixaId")
    fun getUmaCaixaComEmails(caixaId: Long): CaixaComEmails
}