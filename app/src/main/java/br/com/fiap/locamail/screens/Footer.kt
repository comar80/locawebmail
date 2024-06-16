package br.com.fiap.locamail.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro

@Composable
fun Footer(navController: NavController) {

    val tamanhoIcone = Modifier
        .size(35.dp)

    val tamanhoIconeMenor = Modifier
        .size(20.dp)

    val tamanhoIconeMaior = Modifier
        .size(40.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.email),
                contentDescription = "email",
                modifier = tamanhoIconeMenor.clickable { navController.navigate("entrada") },
                tint = colorResource(id = R.color.preto_locaweb)
            )
            Text(
                text = "Email",
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.escrever),
                contentDescription = "escrever",
                modifier = tamanhoIconeMenor.clickable { navController.navigate("escrever") },
                tint = colorResource(id = R.color.preto_locaweb)
            )
            Text(
                text = "Escrever",
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.buscar),
                contentDescription = "buscar",
                modifier = tamanhoIconeMenor.clickable { navController.navigate("buscar") },
                tint = colorResource(id = R.color.preto_locaweb)
            )
            Text(
                text = "Buscar",
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro
            )
        }
    }
}