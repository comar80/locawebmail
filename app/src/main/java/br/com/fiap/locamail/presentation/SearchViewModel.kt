package br.com.fiap.locamail.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.locamail.data.apiRepository.EmailApiRepository
import br.com.fiap.locamail.data.network.RetrofitClient
import br.com.fiap.locamail.data.model.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel() : ViewModel() {

    val emailRepository = EmailApiRepository(apiService = RetrofitClient.getApiService())

    private val _emailList = MutableLiveData<List<Email>>()
    val emailList: LiveData<List<Email>> get() = _emailList

    init {
        listEmails()
    }

    fun listEmails() {
        viewModelScope.launch {
            try {
                // Call the API asynchronously and wait for the response
                val response = withContext(Dispatchers.IO) {
                    emailRepository.getEmailsFromApi()
                }

                if (response.isSuccessful) {
                    val emails = response.body() ?: emptyList() // Handle null body
                    _emailList.value = emails
                } else {
                    Log.e("SearchViewModel", "Failed to fetch emails: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Exception fetching emails: ${e.message}")
            }
        }
    }


    suspend fun searchEmails(query: String) {
        val currentList = _emailList.value ?: emptyList()
        val filteredList = currentList.filter { email ->
            email.titulo.contains(query, ignoreCase = true) ||
                    email.conteudo.contains(query, ignoreCase = true) ||
                    email.destinatario.any { it.contains(query, ignoreCase = true) }
        }
        _emailList.value = filteredList
    }

}
