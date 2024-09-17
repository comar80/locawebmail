package br.com.fiap.locamail.data.network

import br.com.fiap.locamail.data.model.EmailCreate
import br.com.fiap.locamail.utils.EmailSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val URL = "http://10.0.2.2:8080/"

    private val authInterceptor =
        AuthInterceptor(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InVzZXJ0ZXN0ZSIsImV4cCI6MTcyNjYxOTUyNH0.14vzYqMoGVdUNkaaFT58U_unABaq8okKeUskhVAsCLk")

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofitFactory: Retrofit = Retrofit
        .Builder()
        .baseUrl(URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService {
        return retrofitFactory.create(ApiService::class.java)
    }

}

