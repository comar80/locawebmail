package br.com.fiap.locamail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.locamail.model.Email

@Dao
interface EmailDao {

    @Insert
    fun salvar(email: Email): Long

    @Query("UPDATE tbl_email SET caixaEmailId = :caixaEmailId WHERE emailId = :emailId")
    fun moverEmail(caixaEmailId: Long, emailId: Long): Void

    @Query("SELECT * FROM tbl_email WHERE titulo LIKE :titulo")
    suspend fun buscarEmail(titulo: String):List<Email>
}
