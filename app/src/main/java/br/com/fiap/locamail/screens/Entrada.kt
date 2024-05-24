package br.com.fiap.locamail.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.model.EmailModel
import br.com.fiap.locamail.ui.theme.SfPro
import br.com.fiap.locamail.utils.ReadJSONFromAssets
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson

@Composable
fun EntradaScreen(navController: NavController, context: Context) {

    val jsonString = ReadJSONFromAssets(context, "emails.json")
    val listaEmail = Gson().fromJson(jsonString, Array<EmailModel>::class.java).asList()

    val tamanhoIcone = Modifier
        .size(35.dp)

    val tamanhoIconeMenor = Modifier
        .size(20.dp)

    val tamanhoIconeMaior = Modifier
        .size(40.dp)

    val listaIcones = arrayOf(
        R.drawable.enviadas,
        R.drawable.favoritar_solid,
        R.drawable.entrada,
        R.drawable.arquivo,
        R.drawable.lixo
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "menu",
                    modifier = tamanhoIcone
                        .padding(top = 10.dp),
                    tint = colorResource(id = R.color.preto_locaweb)
                )
                Text(
                    text = "Entrada",
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.preto_locaweb),
                    fontFamily = SfPro
                )
                Image(
                    painter = painterResource(id = R.drawable.locaweb),
                    contentDescription = "logo",
                    modifier = tamanhoIconeMaior
                )
            }

            Divider(thickness = 1.dp)

            IconesCaixas(listaIcones)

            Box(modifier = Modifier.height(500.dp)) {
                LazyColumn {
                    items(listaEmail.size) { item ->
                        val nome = listaEmail[item].nome
                        val horario = listaEmail[item].horario
                        val titulo = listaEmail[item].titulo
                        val previa = listaEmail[item].previa
                        val foto = listaEmail[item].foto
                        CardEmail(nome!!, horario!!, titulo!!, previa!!, foto!!)
                     }
                }
            }

            Divider(thickness = 1.dp)

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
                        modifier = tamanhoIconeMenor,
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
                        modifier = tamanhoIconeMenor,
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
                        modifier = tamanhoIconeMenor,
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

    }
}


@Composable
fun CardEmail(nome: String, horario: String, titulo: String, previa: String, foto: String) {
    Divider(thickness = 1.dp)
    Row {
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
                fontFamily = SfPro)

            Text(text = previa,
                modifier = Modifier.padding(start = 10.dp),
                color = colorResource(id = R.color.preto_locaweb),
                fontFamily = SfPro)
        }
    }
}

@Composable
fun IconesCaixas(listaIcones: Array<Int>) {

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
        ) {
        val itemCount = listaIcones.size
        items(itemCount){ item ->
            RowItem(
                itemIndex = item,
                painter = listaIcones
            )
        }
    }
}

@Composable
fun RowItem(itemIndex: Int, painter: Array<Int>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(painter = painterResource(id = painter[itemIndex]),
            contentDescription = "icone",
            modifier = Modifier
                .size(25.dp),
            tint = colorResource(id = R.color.preto_locaweb)
        )
    }
}