package br.com.fiap.locamail.mockemail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class SearchViewModelMock(val emailRepository: EmailRepositoryMock) : ViewModel() {

    val _emailList = MutableLiveData<List<EmailMock>>()
    val emailList: LiveData<List<EmailMock>> get() = _emailList

    init {
        listEmails()
    }

    fun listEmails() {
        viewModelScope.launch {
            val emails = emailRepository.getAllEmails()
            _emailList.value = emails
        }
    }

    suspend fun searchEmails(query: String) {
        val filteredList = emailRepository.getAllEmails().filter { email ->
            email.subject.contains(query, ignoreCase = true) ||
                    email.body.contains(query, ignoreCase = true) ||
                    email.recipients.any { it.contains(query, ignoreCase = true) }
        }
        _emailList.value = filteredList
    }
}
