package br.com.fiap.locamail.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.locamail.database.repository.EmailRepository
import br.com.fiap.locamail.model.Email
import kotlinx.coroutines.launch

open class SearchViewModel(context: Context) : ViewModel() {

    val emailRepository = EmailRepository(context)

    val _emailList = MutableLiveData<List<Email>>()
    val emailList: LiveData<List<Email>> get() = _emailList

    init {
        listEmails()
    }

    fun listEmails() {
        viewModelScope.launch {
            val emails = emailRepository.buscarTodosEmails()
            _emailList.value = emails
        }
    }

    suspend fun searchEmails(query: String) {
        val filteredList = emailRepository.buscarTodosEmails().filter { email ->
            email.titulo.contains(query, ignoreCase = true) ||
                    email.conteudo.contains(query, ignoreCase = true) ||
                    email.destinatario.any { it.contains(query, ignoreCase = true) }
        }
        _emailList.value = filteredList
    }
}
