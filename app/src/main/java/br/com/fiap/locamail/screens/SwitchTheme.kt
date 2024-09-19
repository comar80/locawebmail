package br.com.fiap.locamail.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import br.com.fiap.locamail.data.apiRepository.UserApiRepository
import br.com.fiap.locamail.data.network.RetrofitClient
import br.com.fiap.locamail.data.network.UserSession
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SwitchTheme(isDarkMode: Boolean, userName: String, onThemeChange: (Boolean) -> Unit) {
    val userApiRepository = UserApiRepository(apiService = RetrofitClient.getApiService())
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier.padding(start = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = isDarkMode,
            onCheckedChange = { newCheckedState ->
                onThemeChange(newCheckedState)

                coroutineScope.launch {
                    try {
                        userApiRepository.updateUserTema(userName, newCheckedState)
                        Log.d("SwitchTheme", "Theme updated successfully in the database")
                    } catch (e: Exception) {
                        Log.e("SwitchTheme", "Failed to update theme: ${e.message}")
                    }
                }
            },
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = "Modo Escuro",
            textAlign = TextAlign.Center)
    }
}
