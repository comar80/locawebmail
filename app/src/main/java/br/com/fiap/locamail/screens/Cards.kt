package br.com.fiap.locamail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardEmail(nome: String, horario: String, titulo: String, previa: String, conteudo: String,  foto: String, navController: NavController) {
    Divider(thickness = 1.dp)
    Row(modifier = Modifier.clickable {
        navController.navigate("email/${titulo}/${nome}/${horario}/${conteudo}")
    }) {
        Image(
            painter = rememberAsyncImagePainter(foto),
            contentDescription = "logo",
            modifier = Modifier.size(30.dp)
        )
        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = nome, color = colorResource(id = R.color.preto_locaweb))
                Text(text = horario, color = colorResource(id = R.color.preto_locaweb))
            }
            Text(text = titulo,
                modifier = Modifier.padding(start = 10.dp),
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro
            )

            Text(text = previa,
                modifier = Modifier.padding(start = 10.dp),
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro
            )
        }
    }
}