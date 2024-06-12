package br.com.fiap.locamail.model

import androidx.room.Embedded
import androidx.room.Relation

data class CaixaComEmails(
    @Embedded val caixaEmail: CaixaEmail,
    @Relation(parentColumn = "caixaId", entityColumn = "caixaEmailId")
    val emails: List<Email>

)
