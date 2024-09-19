package br.com.fiap.locamail.data.apiRepository

import android.util.Log
import br.com.fiap.locamail.data.model.Email
import br.com.fiap.locamail.data.model.EmailCreate
import br.com.fiap.locamail.data.network.ApiService
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmailApiRepository(private val apiService: ApiService) {

    suspend fun getEmailsFromApi(): Response<List<Email>> {
        return withContext(Dispatchers.IO) {
            apiService.getEmails().execute()
        }
    }

    fun moverEmail(caixaEmailId: String, emailId: String): Call<Void> {
        val existingEmail = getEmailByIdSync(emailId)
        val updatedEmail = existingEmail.copy(caixaEmailId = caixaEmailId)
        return apiService.moverEmail(emailId, updatedEmail)
    }

    fun getEmailByIdSync(emailId: String): Email {
        val call = apiService.getEmailById(emailId)
        val response = call.execute()

        return if (response.isSuccessful) {
            response.body() ?: throw IllegalStateException("Email não encontradao")
        } else {
            throw IllegalStateException("Erro ao buscar email: ${response.code()}")
        }
    }

    fun createEmail(email: EmailCreate, callback: (EmailCreate?) -> Unit) {
        apiService.createEmail(email).enqueue(object : Callback<EmailCreate> {
            override fun onResponse(call: Call<EmailCreate>, response: Response<EmailCreate>) {
                if (response.isSuccessful) {
                    callback(response.body())
                    Log.i("resposta", "${response.body()}")
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<EmailCreate>, t: Throwable) {
                callback(null)
            }
        })
    }
}
