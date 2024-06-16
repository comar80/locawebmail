package br.com.fiap.locamail.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.locamail.database.repository.EmailRepository
import br.com.fiap.locamail.model.EmailModel
import br.com.fiap.locamail.presentation.SearchViewModel
import br.com.fiap.locamail.utils.ReadJSONFromAssets
import com.google.gson.Gson

@Composable
fun BuscaScreen(navController: NavController, context: Context) {

    val jsonString = ReadJSONFromAssets(context, "emails.json")
    val listaEmail = Gson().fromJson(jsonString, Array<EmailModel>::class.java).asList()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var buscaEmail by remember { mutableStateOf("") }


    // Obter contexto
    val viewModel = viewModel<SearchViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)

    Box() {




    }
}




