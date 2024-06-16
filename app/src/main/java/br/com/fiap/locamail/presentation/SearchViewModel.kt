package br.com.fiap.locamail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.locamail.database.repository.EmailRepository
import br.com.fiap.locamail.model.Email
import kotlinx.coroutines.launch


open class SearchViewModel(
    private val emailRepository: EmailRepository,
    val search: RegistrationSearchEvent
    ): ViewModel() {

        var state by mutableStateOf(RegistrationSearchState())

        private val _searchResults = MutableLiveData<List<Email>>()
        val searchResults: LiveData<List<Email>> = _searchResults


     fun searchForEmail(query: String) {
        viewModelScope.launch {
            val results = emailRepository.buscarEmail(query)
            _searchResults.value = results
        }

    }

}