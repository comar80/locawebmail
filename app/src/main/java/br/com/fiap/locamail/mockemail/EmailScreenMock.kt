package br.com.fiap.locamail.mockemail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@Composable
fun EmailScreenMock(viewModel: SearchViewModelMock, navController: NavController) {

    var searchQuery by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Row(
            modifier = Modifier
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                                    searchQuery = it
                                   coroutineScope.launch { viewModel.searchEmails(it) }
                        
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeholder = { Text(text = "Buscar...") }
                )

                Icon(
                    painter = painterResource(id = R.drawable.buscar),
                    contentDescription = "buscar",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 10.dp, start = 10.dp)
                        .clickable {
                                   coroutineScope.launch { viewModel.searchEmails(searchQuery) }
                        },
                    tint = colorResource(id = R.color.preto_locaweb)
                )
            }
        }

        val emailList by viewModel.emailList.observeAsState(emptyList())
        LazyColumn {
            items(emailList) { email ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Assunto: ${email.subject}", fontWeight = FontWeight.Bold)
                    Text(text = "Conteúdo: ${email.body}")
                    Text(text = "Destinatários: ")
                    email.recipients.forEach { recipient ->
                        Text(text = recipient, modifier = Modifier.padding(start = 8.dp))
                    }
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEmailScreen() {
    val mockNavController = rememberNavController()
    val mockViewModel = MockSearchViewModel()

    EmailScreenMock(viewModel = mockViewModel, navController = mockNavController)
}
