package br.com.fiap.locamail.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {
    @Volatile
    private var authToken: String? = null

    fun setToken(token: String) {
        this.authToken = token
        Log.d("AuthInterceptor", "Token set: $token")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Only add the Authorization header if the token is not null
        val newRequest = if (authToken != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $authToken")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}

