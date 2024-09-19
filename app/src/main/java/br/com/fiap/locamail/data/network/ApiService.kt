package br.com.fiap.locamail.data.network

import br.com.fiap.locamail.data.model.Email
import br.com.fiap.locamail.data.model.EmailCreate
import br.com.fiap.locamail.data.model.LoginRequest
import br.com.fiap.locamail.data.model.LoginResponse
import br.com.fiap.locamail.data.model.UserCreate
import br.com.fiap.locamail.data.model.UserGet
import br.com.fiap.locamail.data.model.UserUpdate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    //EMAIL
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

    //USER
    @POST("api/user/insert")
    fun createUser(@Body user: UserCreate): Call<UserCreate>

    @POST("auth/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("api/user/update-tema/{userName}")
    fun getUserByUserName(@Path("userName") userName: String): Call<UserGet>

    @PUT("api/user/update-tema/{id}")
    fun updateUserTema(
        @Path("id") userId: String,
        @Body updatedUser: UserUpdate
    ): Call<Void>
}