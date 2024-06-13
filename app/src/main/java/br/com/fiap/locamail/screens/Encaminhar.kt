package br.com.fiap.locamail.screens


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.database.repository.EmailRepository
import br.com.fiap.locamail.model.Email
import br.com.fiap.locamail.ui.theme.SfPro
import br.com.fiap.locamail.utils.getFileNameFromUri
import br.com.fiap.locamail.utils.getSizeFromUri
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncaminharScreen(navController: NavController, titulo: String, conteudo: String) {

    var destinatarioState by remember { mutableStateOf("") }
    var ccState by remember { mutableStateOf("") }
    var ccoState by remember { mutableStateOf("") }
    var tituloState by remember { mutableStateOf(titulo) }
    var conteudoState by remember { mutableStateOf(conteudo) }

    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        result.value = it
    }
    val listaAnexo = remember { mutableListOf<Uri?>() }
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)

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

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.anexo),
                        contentDescription = "anexo",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp)
                            .clickable {
                                launcher.launch(arrayOf("*/*"))
                            },
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    OutlinedButton(
                        onClick = {
                            val destinatario = ArrayList<String>()
                            destinatario.add(destinatarioState)

                            val destCopia = ArrayList<String>()
                            destCopia.add(ccState)

                            val destOculto = ArrayList<String>()
                            destOculto.add(ccoState)

                            val anexo = ArrayList<String>()
                            anexo.add(listaAnexo.toString())

                            val email = Email(
                                emailId = 0,
                                caixaEmailId = 2,
                                remetente = destinatarioState,
                                destinatario = destinatario,
                                destCopia = destCopia,
                                destOculto = destOculto,
                                horario = LocalDateTime.now(),
                                titulo = tituloState,
                                conteudo = conteudoState,
                                fotoRemetente = "https://i.ibb.co/8dXv21g/locaweb.png",
                                anexo = anexo
                            )
                            emailRepository.salvar(email)

                            val toast = Toast.makeText(context, "Mensagem enviada com sucesso!", Toast.LENGTH_LONG)
                            toast.show()
                        },
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


                Column(modifier = Modifier
                    .fillMaxSize()) {
                    result.value?.let { anexo ->
                        listaAnexo.add(result.value)
                        for(item in listaAnexo){
                            val nomeAnexo = getFileNameFromUri(item!!, context)
                            val tamanhoAnexo = getSizeFromUri(item, context)
                            AnexoCard(nomeAnexo, tamanhoAnexo)
                        }
                        println("lista anexo: $listaAnexo")
                    }
                }
            }
        }
    }
}