package br.com.fiap.locamail.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.model.MenuItem

@Composable
fun ListaIcones(navController: NavController) {

    val listaIcones = listOf(
        MenuItem(
            title = "Entrada",
            selectedIcon = painterResource(id = R.drawable.entrada),
            unselectedIcon = painterResource(id = R.drawable.entrada),
            path = "entrada"
        ),
        MenuItem(
            title = "Enviadas",
            selectedIcon = painterResource(id = R.drawable.enviadas),
            unselectedIcon = painterResource(id = R.drawable.enviadas),
            path = "enviadas"
        ),
        MenuItem(
            title = "Importante",
            selectedIcon = painterResource(id = R.drawable.favoritar_solid),
            unselectedIcon = painterResource(id = R.drawable.favoritar_solid),
            path = "importante"
        ),
        MenuItem(
            title = "Arquivo",
            selectedIcon = painterResource(id = R.drawable.arquivo),
            unselectedIcon = painterResource(id = R.drawable.arquivo),
            path = "arquivo"
        ),
        MenuItem(
            title = "Lixeira",
            selectedIcon = painterResource(id = R.drawable.lixo),
            unselectedIcon = painterResource(id = R.drawable.lixo),
            path = "lixeira"
        )
    )

    IconesCaixas(listaIcones, navController)
}

@Composable
fun IconesCaixas(listaIcones: List<MenuItem>, navController: NavController) {

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(listaIcones) {item ->
            RowItem(painter = item.selectedIcon,
                navController,
                path = item.path
            )
        }
    }
}

@Composable
fun RowItem(painter: Painter, navController: NavController, path: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(painter = painter,
            contentDescription = "icone",
            modifier = Modifier
                .size(25.dp)
                .clickable(onClick = { navController.navigate(path) }),
            tint = colorResource(id = R.color.preto_locaweb)
        )
    }
}