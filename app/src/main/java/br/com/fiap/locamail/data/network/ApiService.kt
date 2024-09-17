package br.com.fiap.locamail.data.network

import br.com.fiap.locamail.data.model.Email
import br.com.fiap.locamail.data.model.EmailCreate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/email/all")
    fun getEmails(): Call<List<Email>>

    @GET("api/email/id/{id}")
    fun getEmailById(@Path("id") emailId: String): Call<Email>

    @POST("api/email/insert")
    fun createEmail(@Body email: EmailCreate): Call<EmailCreate>

    @PUT("api/email/update/{id}")
    fun moverEmail(
        @Path("id") emailId: String,
        @Body updatedEmail: Email
    ): Call<Void>
}