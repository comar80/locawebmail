package br.com.fiap.locamail.data.apiRepository

import android.util.Log
import br.com.fiap.locamail.MainActivity
import br.com.fiap.locamail.data.model.Email
import br.com.fiap.locamail.data.model.LoginRequest
import br.com.fiap.locamail.data.model.LoginResponse
import br.com.fiap.locamail.data.model.UserCreate
import br.com.fiap.locamail.data.model.UserGet
import br.com.fiap.locamail.data.model.UserUpdate
import br.com.fiap.locamail.data.network.ApiService
import br.com.fiap.locamail.data.network.RetrofitClient
import br.com.fiap.locamail.data.network.UserSession
import br.com.fiap.locamail.utils.toUserUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    fun loginUser(
        userName: String,
        password: String,
        activity: MainActivity,
        onLoginSuccess: (LoginResponse?, UserGet?) -> Unit,
        onLoginFailure: () -> Unit
    ) {
        val loginRequest = LoginRequest(userName, password)

        apiService.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    val loginResponse = response.body()
                    val token = loginResponse?.token

                    if (token != null) {
                        RetrofitClient.updateToken(token)
                        UserSession.userName = userName

                        apiService.getUserByUserName(userName).enqueue(object : Callback<UserGet> {
                            override fun onResponse(
                                call: Call<UserGet>,
                                userResponse: Response<UserGet>
                            ) {
                                val userGet = userResponse.body()
                                if (userGet != null) {
                                    activity.updateDarkMode(userGet.tema_escuro)
                                }
                                if (userResponse.isSuccessful) {
                                    onLoginSuccess(loginResponse, userGet)
                                } else {
                                    Log.e("UserApiRepository", "Error fetching user info")
                                    onLoginFailure()
                                }
                            }

                            override fun onFailure(call: Call<UserGet>, t: Throwable) {
                                Log.e(
                                    "UserApiRepository",
                                    "Failed to fetch user info: ${t.message}"
                                )
                                onLoginFailure()
                            }
                        })
                    } else {
                        Log.e("Login", "Token is null")
                        onLoginFailure()
                    }
                } else {
                    onLoginFailure()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onLoginFailure()
            }
        })
    }

    suspend fun getUserByUserName(userName: String): UserGet {
        return withContext(Dispatchers.IO) {
            val call = apiService.getUserByUserName(userName)
            val response = call.execute()
            Log.i("userget", "getUserByUserName: ${response.body()}")

            if (response.isSuccessful) {

                response.body() ?: throw IllegalStateException("User não encontrado")
            } else {
                throw IllegalStateException("Erro ao buscar user: ${response.code()}")
            }
        }
    }



    suspend fun updateUserTema(userName: String, tema_escuro: Boolean) {

        val existingUser = getUserByUserName(userName)
        Log.i("user", "existingUser: ${existingUser}")
        val userId = existingUser.id
        val userUpdate = existingUser.toUserUpdate().copy(tema_escuro = tema_escuro)


        // Make a network call to update the user theme
        val call = apiService.updateUserTema(userId, userUpdate)
        val response = withContext(Dispatchers.IO) { call.execute() }

        if (!response.isSuccessful) {
            throw IllegalStateException("Erro ao atualizar tema: ${response.code()}")
        }
    }


}

