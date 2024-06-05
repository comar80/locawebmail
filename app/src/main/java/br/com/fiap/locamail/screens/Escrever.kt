package br.com.fiap.locamail.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscreverScreen(navController: NavController) {

    var destinatarioState by remember { mutableStateOf("") }
    var ccState by remember { mutableStateOf("") }
    var ccoState by remember { mutableStateOf("") }
    var tituloState by remember { mutableStateOf("") }
    var conteudoState by remember { mutableStateOf("") }

    Box() {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.voltar),
                    contentDescription = "voltar",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 10.dp)
                        .clickable { navController.popBackStack() },
                    tint = colorResource(id = R.color.preto_locaweb)
                )
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.lixo),
                        contentDescription = "lixeira",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.opcoes),
                        contentDescription = "opcoes",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                }

            }

            Divider(thickness = 1.dp)

            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp),
                ) {
                    TextField(
                        value = tituloState,
                        onValueChange = { tituloState = it },
                        label = { Text(
                            text = "Título",
                            fontFamily = SfPro,
                            fontWeight = FontWeight.Bold,
                        ) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        )
                        TextField(
                            value = destinatarioState,
                            onValueChange = { destinatarioState = it },
                            label = { Text(
                                text = "Para",
                                fontFamily = SfPro,
                                fontWeight = FontWeight.Bold,
                            ) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                            )
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                    ) {
                        TextField(
                            value = ccState,
                            onValueChange = { ccState = it },
                            modifier = Modifier
                                .width(180.dp)
                                .padding(end = 10.dp),
                            label = { Text(
                                text = "cc",
                                fontFamily = SfPro,
                                fontWeight = FontWeight.Bold,
                            ) },
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        )
                        TextField(
                            value = ccoState,
                            onValueChange = { ccoState = it },
                            modifier = Modifier
                                .width(180.dp),
                            label = { Text(
                                text = "cco",
                                fontFamily = SfPro,
                                fontWeight = FontWeight.Bold,
                            ) },
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                            )
                    }
                }
                    OutlinedTextField(
                        value = conteudoState,
                        onValueChange = { conteudoState = it },
                        label = { Text(
                            text = "",
                            fontFamily = SfPro,
                            fontWeight = FontWeight.Bold,
                        ) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                        )

            }



            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(id = R.color.azul_locaweb),
                    ),
                    border = BorderStroke(1.dp, colorResource(id = R.color.azul_locaweb))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.enviadas),
                        contentDescription = "responder",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(text = "Enviar", fontFamily = SfPro)
                }

            }
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun EmailScreenPV() {
    val navController = rememberNavController()

    EscreverScreen(navController = navController)
}