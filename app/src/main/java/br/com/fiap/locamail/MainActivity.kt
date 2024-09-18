package br.com.fiap.locamail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.presentation.SearchViewModel
import br.com.fiap.locamail.screens.ArquivoScreen
import br.com.fiap.locamail.screens.BuscaScreen
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

            var isDarkMode by remember {
                mutableStateOf(false)
            }
            LocaMailTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {

                        composable(route = "login") { Login(navController) }

                        composable(route = "cadastro") { Cadastro(navController) }

                        composable(route = "email/{titulo}/{nome}/{horario}/{conteudo}/{emailId}") {
                            val titulo = it.arguments?.getString("titulo")
                            val nome = it.arguments?.getString("nome")
                            val horario = it.arguments?.getString("horario")
                            val conteudo = it.arguments?.getString("conteudo")
                            val emailId = it.arguments?.getString("emailId")

                            EmailScreen(navController,
                            onCalendarIconClick = {
                            val mIntent = Intent(Intent.ACTION_EDIT)
                            mIntent.type = "vnd.android.cursor.item/event"
                            mIntent.putExtra("title", titulo)
                            startActivity(mIntent)
                        },
                            titulo!!, nome!!, horario!!, conteudo!!, emailId!!) }

                        composable(route = "entrada") { EntradaScreen(navController, baseContext, isDarkMode = isDarkMode) {
                            isDarkMode = !isDarkMode
                        }
                        }
                        composable(route = "enviadas") { EnviadasScreen(navController, baseContext, isDarkMode = isDarkMode) {
                            isDarkMode = !isDarkMode
                        } }
                        composable(route = "importante") { ImportanteScreen(navController, baseContext, isDarkMode = isDarkMode) {
                            isDarkMode = !isDarkMode
                        } }
                        composable(route = "arquivo") { ArquivoScreen(navController, baseContext, isDarkMode = isDarkMode) {
                            isDarkMode = !isDarkMode
                        } }
                        composable(route = "lixeira") { LixeiraScreen(navController, baseContext, isDarkMode = isDarkMode) {
                            isDarkMode = !isDarkMode
                        } }
                        composable(route = "escrever") { EscreverScreen(navController) }
                        composable(route = "buscar") {
                            val mockViewModel = SearchViewModel(baseContext)
                            BuscaScreen(viewModel = mockViewModel, navController)
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