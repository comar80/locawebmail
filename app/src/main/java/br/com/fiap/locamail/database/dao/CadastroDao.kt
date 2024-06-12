package br.com.fiap.locamail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.fiap.locamail.model.Cadastro
import br.com.fiap.locamail.model.CaixaComEmails

@Dao
interface CadastroDao {

    @Insert
    fun salvar(cadastro: Cadastro): Long

    @Query("SELECT * FROM tbl_usuario WHERE user = :user")
    fun buscarUsuario(user: String): Cadastro


}