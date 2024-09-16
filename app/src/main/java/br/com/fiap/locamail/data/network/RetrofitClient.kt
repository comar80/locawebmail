package br.com.fiap.locamail.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val URL = "http://10.0.2.2:8080/"

    private val authInterceptor =
        AuthInterceptor(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InRlc3RlIiwiZXhwIjoxNzI2NTMwNjA0fQ.xClTFhby4dF5L9xHrOlfKpWrYuICEPvsmeqrK9pkOes")

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

