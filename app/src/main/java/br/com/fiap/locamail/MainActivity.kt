package br.com.fiap.locamail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.mockemail.EmailScreenMock
import br.com.fiap.locamail.mockemail.MockSearchViewModel
import br.com.fiap.locamail.screens.ArquivoScreen
import br.com.fiap.locamail.screens.Cadastro
import br.com.fiap.locamail.screens.EmailScreen
import br.com.fiap.locamail.screens.EncaminharScreen
import br.com.fiap.locamail.screens.EntradaScreen
import br.com.fiap.locamail.screens.EnviadasScreen
import br.com.fiap.locamail.screens.EscreverScreen
import br.com.fiap.locamail.screens.ImportanteScreen
import br.com.fiap.locamail.screens.LixeiraScreen
import br.com.fiap.locamail.screens.Login
import br.com.fiap.locamail.screens.ResponderScreen
import br.com.fiap.locamail.ui.theme.LocaMailTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocaMailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {

                        composable(route = "login") { Login(navController) }

                        composable(route = "cadastro") { Cadastro(navController) }

                        composable(route = "email/{titulo}/{nome}/{horario}/{conteudo}") {
                            val titulo = it.arguments?.getString("titulo")
                            val nome = it.arguments?.getString("nome")
                            val horario = it.arguments?.getString("horario")
                            val conteudo = it.arguments?.getString("conteudo")

                            EmailScreen(navController,
                            onCalendarIconClick = {
                            val mIntent = Intent(Intent.ACTION_EDIT)
                            mIntent.type = "vnd.android.cursor.item/event"
                            mIntent.putExtra("title", titulo)
                            startActivity(mIntent)
                        },
                            titulo!!, nome!!, horario!!, conteudo!!) }

                        composable(route = "entrada") { EntradaScreen(navController, baseContext) }
                        composable(route = "enviadas") { EnviadasScreen(navController, baseContext) }
                        composable(route = "importante") { ImportanteScreen(navController, baseContext) }
                        composable(route = "arquivo") { ArquivoScreen(navController, baseContext) }
                        composable(route = "lixeira") { LixeiraScreen(navController, baseContext) }
                        composable(route = "escrever") { EscreverScreen(navController) }
                        composable(route = "buscar") {
                            val mockViewModel = MockSearchViewModel()
                            EmailScreenMock(viewModel = mockViewModel, navController)
                        }

                        composable(route = "responder/{titulo}/{nome}/{conteudo}") {
                            val titulo = it.arguments?.getString("titulo")
                            val nome = it.arguments?.getString("nome")
                            val conteudo = it.arguments?.getString("conteudo")

                            ResponderScreen(navController, titulo!!, nome!!, conteudo!!)
                        }

                        composable(route = "encaminhar/{titulo}/{conteudo}") {
                            val titulo = it.arguments?.getString("titulo")
                            val conteudo = it.arguments?.getString("conteudo")

                            EncaminharScreen(navController, titulo!!, conteudo!!)
                        }

                    }


                }
            }
        }
    }
}