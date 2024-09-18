package br.com.fiap.locamail.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.presentation.MainViewModel
import br.com.fiap.locamail.R
import br.com.fiap.locamail.data.apiRepository.EmailApiRepository
import br.com.fiap.locamail.data.apiRepository.UserApiRepository
import br.com.fiap.locamail.data.model.UserCreate
import br.com.fiap.locamail.data.network.RetrofitClient
import br.com.fiap.locamail.presentation.RegistrationFormEvent
import br.com.fiap.locamail.database.repository.CadastroRepository
import br.com.fiap.locamail.model.Cadastro
import br.com.fiap.locamail.ui.theme.SfPro
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController) {

    var nomeState by remember { mutableStateOf("") }
    var sobrenomeState by remember { mutableStateOf("") }
    var userState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var confirmaSenhaState by remember { mutableStateOf("") }

    // Obter contexto
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    val cadastroRepository = CadastroRepository(context)

    val userRepository = UserApiRepository(apiService = RetrofitClient.getApiService())
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.preto_locaweb))
            .padding(start = 32.dp, end = 32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastro",
            fontFamily = SfPro,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.locaweb),
                    contentDescription = "logo",
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = state.nome,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationFormEvent.NomeChanged(it))
                nomeState = it
            },
            isError = state.nomeError != null,
            label = { Text(
                text = "Nome",
                fontFamily = SfPro,
                fontWeight = FontWeight.Bold
            ) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (state.nomeError != null) {
            Text(
                text = state.nomeError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.sobrenome,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationFormEvent.SobrenomeChanged(it))
                sobrenomeState = it },
            isError = state.sobrenomeError != null,
            label = { Text(text = "Sobrenome", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (state.sobrenomeError != null) {
            Text(
                text = state.sobrenomeError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.user,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationFormEvent.UserChanged(it))
                userState = it },
            isError = state.userError != null,
            label = { Text(text = "Nome de Usuário",fontFamily = SfPro, fontWeight = FontWeight.Bold
            ) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (state.userError != null) {
            Text(
                text = state.userError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationFormEvent.PasswordChanged(it))
                senhaState = it
            },
            isError = state.passwordError != null,
            label = { Text(text = "Senha", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* TODO: Action when done typing */ }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White)
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.repeatedPassword,
            onValueChange = {
                viewModel.onRegisterEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                confirmaSenhaState = it
            },
            isError = state.repeatedPasswordError != null,
            label = { Text(text = "Confirmar Senha", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* TODO: Action when done typing */ }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White)
        )
        if (state.repeatedPasswordError != null) {
            Text(
                text = state.repeatedPasswordError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                viewModel.onRegisterEvent(RegistrationFormEvent.Submit)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(6.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
        ) {
            Text(text = "Criar Login", fontFamily = SfPro, fontSize = 20.sp, color = colorResource(id = R.color.preto_locaweb))
        }
    }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is MainViewModel.ValidationEvent.Success -> {

                    val user = UserCreate(
                        name = nomeState,
                        lastName = sobrenomeState,
                        userName = userState,
                        password = senhaState
                    )

                    coroutineScope.launch {
                        userRepository.createUser(user) { createdUser ->
                            if (createdUser != null) {
                                navController.navigate("login")
                                val toast = Toast.makeText(context, "Usuário criado com sucesso!", Toast.LENGTH_LONG)
                                toast.show()
                            } else {
                                // Handle failure, show error message
                                Log.e("CreateUser", "Failed to create user")
                            }
                        }
                    }
                }
            }
        }
    }
}