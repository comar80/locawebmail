package br.com.fiap.locamail.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import br.com.fiap.locamail.data.model.Email
import br.com.fiap.locamail.data.network.RetrofitClient
import br.com.fiap.locamail.database.repository.CaixaRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EntradaScreen(navController: NavController, context: Context, isDarkMode: Boolean, onThemeChange: (Boolean)->Unit) {

    val caixaRepository = CaixaRepository(context)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var emailList by remember { mutableStateOf<List<Email>>(emptyList()) }

    LaunchedEffect(Unit) {
        val call = RetrofitClient().getApiService().getEmails()

        call.enqueue(object : Callback<List<Email>> {
            override fun onResponse(
                call: Call<List<Email>>,
                response: Response<List<Email>>
            ) {
                response.body()?.let {
                    emailList = it
                }
            }

            override fun onFailure(call: Call<List<Email>>, t: Throwable) {
                Log.i("Error", "Erro da Reposta: ${t.message}")
            }
        })
    }
    
    ModalNavigationDrawer(drawerContent = {
        MenuLateral(navController, isDarkMode, onThemeChange)
    },
        drawerState = drawerState
    ) {

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Header(onNavigationIconClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }, "Entrada")

                Divider(thickness = 1.dp)

                ListaIcones(navController)

                Box(modifier = Modifier.height(500.dp)) {
                    if (emailList.isNotEmpty()) {
                        LazyColumn {
                            items(emailList.size) { index ->
                                val email = emailList[index]

                                val parsedDate = ZonedDateTime.parse(email.horario)
                                val formattedHorario = parsedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm"))
                                CardEmail(
                                    nome = email.destinatario.joinToString(", "),
                                    horario = formattedHorario,
                                    titulo = email.titulo,
                                    previa = email.conteudo.take(50),
                                    conteudo = email.conteudo,
                                    foto = email.fotoRemetente!!,
                                    emailId = email.emailId,
                                    navController = navController,
                                    listaEmails = emailList
                                )
                            }
                        }
                    } else {
                        Text(text = "Caixa de Emails vazia", modifier = Modifier.padding(16.dp))
                    }
                }

                Divider(thickness = 1.dp)

                Footer(navController)
            }

        }

    }
}