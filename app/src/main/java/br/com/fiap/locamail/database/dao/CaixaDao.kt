package br.com.fiap.locamail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.fiap.locamail.model.CaixaComEmails
import br.com.fiap.locamail.model.CaixaEmail

@Dao
interface CaixaDao {

    @Insert
    fun salvar(caixa: CaixaEmail): Long

    @Query("SELECT * FROM tbl_caixa")
    fun getCaixas(): List<CaixaEmail>

    @Transaction
    @Query("SELECT * FROM tbl_caixa")
    fun getCaixaComEmails(): List<CaixaComEmails>

    @Transaction
    @Query("SELECT * FROM tbl_caixa WHERE caixaId = :caixaId")
    fun getUmaCaixaComEmails(caixaId: Long): CaixaComEmails
}