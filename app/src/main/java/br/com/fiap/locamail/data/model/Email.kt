package br.com.fiap.locamail.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Email(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var emailId: String,
    @SerializedName("caixaEmail_id") var caixaEmailId: String,
    @SerializedName("user_id") var userId: String,
    @SerializedName("email_de") var remetente: String,
    @SerializedName("email_para" )var destinatario: ArrayList<String>,
    @SerializedName("email_cc") var destCopia: ArrayList<String>? = null,
    @SerializedName("email_cco") var destOculto: ArrayList<String>? = null,
    var horario: String,
    var titulo: String,
    var conteudo: String,
    @SerializedName("foto") var fotoRemetente: String? = null,
    var anexo: ArrayList<String>? = null
)
