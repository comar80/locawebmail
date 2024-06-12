package br.com.fiap.locamail.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
data class Cadastro(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nome: String = "",
    var sobrenome: String = "",
    var user: String = "",
    var senha: String = ""
)
