package br.com.fiap.locamail.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class EmailCreate(
    var caixaEmail_id: String,
    var user_id: String,
    var email_de: String,
    var email_para: ArrayList<String>,
    var email_cc: ArrayList<String>? = null,
    var email_cco: ArrayList<String>? = null,

    var horario: String,
    var titulo: String,
    var conteudo: String,

    var foto: String? = null,
    var anexo: ArrayList<String>? = null
)
