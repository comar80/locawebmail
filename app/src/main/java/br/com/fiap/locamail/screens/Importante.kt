package br.com.fiap.locamail.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locamail.model.EmailModel
import br.com.fiap.locamail.utils.ReadJSONFromAssets
import com.google.gson.Gson
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import br.com.fiap.locamail.database.repository.CaixaRepository
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun ImportanteScreen(navController: NavController, context: Context) {

    val jsonString = ReadJSONFromAssets(context, "emails.json")
    val listaEmail = Gson().fromJson(jsonString, Array<EmailModel>::class.java).asList()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    ModalNavigationDrawer(drawerContent = {
        MenuLateral(navController)
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
                }, "Importante")

                Divider(thickness = 1.dp)

                ListaIcones(navController)

                Box(modifier = Modifier.height(500.dp)) {
                    LazyColumn {
                        try {
                            val caixaRepository = CaixaRepository(context)
                            val emailsEntrada = caixaRepository.getUmaCaixaComEmails(5)
                            val listaEmails = emailsEntrada.emails

                            items(listaEmails.size) { item ->
                                val nome = listaEmails[item].remetente

                                val horarioCompleto = listaEmails[item].horario
                                val horario = horarioCompleto.format(DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm"))

                                val titulo = listaEmails[item].titulo
                                val previa = listaEmails[item].conteudo
                                val foto = listaEmails[item].fotoRemetente
                                val conteudo = listaEmails[item].conteudo
                                val emailId = listaEmails[item].emailId.toString()

                                CardEmail(nome, horario!!, titulo, previa, conteudo, foto!!, emailId, navController, listaEmails)
                            }
                        } catch (e: NullPointerException) {
                            Log.e("Erro", "Caixa de Email vazia" )
                        }
                    }
                }

                Divider(thickness = 1.dp)

                Footer(navController)
            }

        }

    }
}