package br.com.fiap.locamail.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tbl_email")
data class Email(
    @PrimaryKey(autoGenerate = true) var emailId: Long,
    var caixaEmailId: Long,
    var remetente: String,
    var destinatario: ArrayList<String>?,
    var horario: LocalDateTime? = LocalDateTime.now(),
    var titulo: String,
    var conteudo: String,
    var fotoRemetente: String? = "https://i.ibb.co/8dXv21g/locaweb.png"
)
