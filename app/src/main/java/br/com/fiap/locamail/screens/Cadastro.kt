package br.com.fiap.locamail.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController) {

    var nomeState by remember { mutableStateOf("") }
    var sobrenomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var confirmaSenhaState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.preto_locaweb))
            .padding(start = 32.dp, end = 32.dp),
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
            value = nomeState,
            onValueChange = { nomeState = it },
            label = { Text(
                text = "Nome",
                fontFamily = SfPro,
                fontWeight = FontWeight.Bold
            ) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = sobrenomeState,
            onValueChange = { sobrenomeState = it },
            label = { Text(text = "Sobrenome", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text(text = "Email",fontFamily = SfPro, fontWeight = FontWeight.Bold
            ) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = senhaState,
            onValueChange = { senhaState = it},
            label = { Text(text = "Senha", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* TODO: Action when done typing */ }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmaSenhaState,
            onValueChange = { confirmaSenhaState = it},
            label = { Text(text = "Confirmar Senha", fontFamily = SfPro, fontWeight = FontWeight.Bold) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* TODO: Action when done typing */ }),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(6.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
        ) {
            Text(text = "Criar Login", fontFamily = SfPro, fontSize = 20.sp, color = colorResource(id = R.color.preto_locaweb))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CadastroScreenPV() {
    val navController = rememberNavController()
    Cadastro(navController = navController)
}
