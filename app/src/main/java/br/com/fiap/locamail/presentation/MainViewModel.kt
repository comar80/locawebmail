package br.com.fiap.locamail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.locamail.validations.ValidateNome
import br.com.fiap.locamail.validations.ValidateUser
import br.com.fiap.locamail.validations.ValidatePassword
import br.com.fiap.locamail.validations.ValidateRepeatedPassword
import br.com.fiap.locamail.validations.ValidateSobrenome
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateNome: ValidateNome = ValidateNome(),
    private val validateSobrenome: ValidateSobrenome = ValidateSobrenome(),
    private val validateUser: ValidateUser = ValidateUser(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onRegisterEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.NomeChanged -> {
                state = state.copy(nome = event.nome)
            }
            is RegistrationFormEvent.SobrenomeChanged -> {
                state = state.copy(sobrenome = event.sobrenome)
            }
            is RegistrationFormEvent.UserChanged -> {
                state = state.copy(user = event.user)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.Submit -> {
                submitRegisterData()
            }
        }
    }

    private fun submitRegisterData() {
        val nomeResult = validateNome.execute(state.nome)
        val sobrenomeResult = validateSobrenome.execute(state.sobrenome)
        val userResult = validateUser.execute(state.user)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(state.password, state.repeatedPassword)

        val hasError = listOf(
            nomeResult,
            sobrenomeResult,
            userResult,
            passwordResult,
            repeatedPasswordResult
        ).any { !it.successful }

        state = state.copy(
            nomeError = nomeResult.errorMessage,
            sobrenomeError = sobrenomeResult.errorMessage,
            userError = userResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatedPasswordError = repeatedPasswordResult.errorMessage
        )
        if(hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun onLoginEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.UserChanged -> {
                state = state.copy(user = event.user)
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is LoginFormEvent.Submit -> {
                submitLoginData()
            }
        }
    }

    private fun submitLoginData() {
        val userResult = validateUser.execute(state.user)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            userResult,
            passwordResult
        ).any { !it.successful }

        state = state.copy(
            userError = userResult.errorMessage,
            passwordError = passwordResult.errorMessage
        )
        if(hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}