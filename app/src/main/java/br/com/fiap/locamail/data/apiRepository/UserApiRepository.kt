package br.com.fiap.locamail.data.apiRepository

import android.util.Log
import br.com.fiap.locamail.data.model.LoginRequest
import br.com.fiap.locamail.data.model.LoginResponse
import br.com.fiap.locamail.data.model.UserCreate
import br.com.fiap.locamail.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserApiRepository(private val apiService: ApiService) {

    fun createUser(user: UserCreate, callback: (UserCreate?) -> Unit) {
        apiService.createUser(user).enqueue(object : Callback<UserCreate> {
            override fun onResponse(call: Call<UserCreate>, response: Response<UserCreate>) {
                if (response.isSuccessful) {
                    callback(response.body())
                    Log.i("resposta", "${response.body()}")
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<UserCreate>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun loginUser(userName: String, password: String, onResult: (LoginResponse?) -> Unit) {
        val loginRequest = LoginRequest(userName, password)

        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body())  // Pass the token back to the caller
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
}
