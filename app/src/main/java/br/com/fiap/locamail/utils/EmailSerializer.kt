package br.com.fiap.locamail.utils

import br.com.fiap.locamail.data.model.EmailCreate
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class EmailSerializer : JsonSerializer<EmailCreate> {
    override fun serialize(src: EmailCreate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()

        // Add fields in the desired order
        jsonObject.addProperty("caixaEmail_id", src.caixaEmail_id)
        jsonObject.addProperty("user_id", src.user_id)
        jsonObject.addProperty("email_de", src.email_de)
        jsonObject.add("email_para", context.serialize(src.email_para))
        jsonObject.add("email_cc", context.serialize(src.email_cc))
        jsonObject.add("email_cco", context.serialize(src.email_cco))
        jsonObject.addProperty("horario", src.horario)
        jsonObject.addProperty("titulo", src.titulo)
        jsonObject.addProperty("conteudo", src.conteudo)
        jsonObject.addProperty("foto", src.foto)
        jsonObject.add("anexo", context.serialize(src.anexo))

        return jsonObject
    }
}
