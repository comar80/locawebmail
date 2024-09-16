package br.com.fiap.locamail.data.network

import br.com.fiap.locamail.data.model.Email
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/email/all")
    fun getEmails(): Call<List<Email>>

    @POST("")
    fun createEmail(@Body item: Email): Email
}