package br.com.fiap.locamail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.model.Email
import br.com.fiap.locamail.ui.theme.SfPro
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardEmail(nome: String, horario: String, titulo: String, previa: String, conteudo: String,  foto: String, navController: NavController, listaEmails: List<Email>) {

    if(listaEmails.isNotEmpty()) {
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
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .heightIn(max = 30.dp),
                    color = colorResource(id = R.color.preto_locaweb),
                    fontFamily = SfPro
                )
            }
        }
    } else {
        Divider(thickness = 1.dp)
        Text(text = "Caixa de Emails vazia",
            modifier = Modifier.padding(start = 10.dp),
            color = colorResource(id = R.color.preto_locaweb),
            fontFamily = SfPro
        )

    }

}

@Composable
fun AnexoCard(nomeAnexo: String, tamanhoAnexo: String) {

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.anexo),
            contentDescription = "anexo",
            modifier = Modifier
                .size(35.dp)
                .padding(start = 10.dp, end = 10.dp),
            tint = colorResource(id = R.color.preto_locaweb)
        )

        Text(text = "$nomeAnexo - $tamanhoAnexo bytes",
            fontFamily = SfPro,
            fontSize = 16.sp)

        Icon(
            painter = painterResource(id = R.drawable.remover),
            contentDescription = "anexo",
            modifier = Modifier
                .size(20.dp)
                .padding(start = 10.dp)
                .clickable {

                },
            tint = colorResource(id = R.color.preto_locaweb)
        )
    }

}