package br.com.fiap.locamail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.fiap.locamail.model.Cadastro

@Dao
interface CadastroDao {

    @Insert
    fun salvar(cadastro: Cadastro): Long


}