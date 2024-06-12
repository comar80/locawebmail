package br.com.fiap.locamail.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_caixa")
data class CaixaEmail(
    @PrimaryKey(autoGenerate = true) var caixaId: Long,
    var nomeCaixa: String
)
