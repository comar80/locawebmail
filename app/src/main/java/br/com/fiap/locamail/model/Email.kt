package br.com.fiap.locamail.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tbl_email")
data class Email(
    @PrimaryKey(autoGenerate = true) var emailId: Long,
    var caixaEmailId: Long,
    var remetente: String,
    var destinatario: ArrayList<String>,
    var destCopia: ArrayList<String>? = null,
    var destOculto: ArrayList<String>? = null,
    var horario: LocalDateTime = LocalDateTime.now(),
    var titulo: String,
    var conteudo: String,
    var fotoRemetente: String? = null,
    var anexo: ArrayList<String>? = null
)
