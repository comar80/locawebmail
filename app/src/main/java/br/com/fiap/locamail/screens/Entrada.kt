package br.com.fiap.locamail.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import br.com.fiap.locamail.model.MenuItem
import br.com.fiap.locamail.ui.theme.SfPro
import br.com.fiap.locamail.utils.ReadJSONFromAssets
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlinx.coroutines.launch

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

    val items = listOf(
        MenuItem(
            title = "Entrada",
            selectedIcon = painterResource(id = R.drawable.entrada),
            unselectedIcon = painterResource(id = R.drawable.entrada),
        ),
        MenuItem(
            title = "Enviadas",
            selectedIcon = painterResource(id = R.drawable.enviadas),
            unselectedIcon = painterResource(id = R.drawable.enviadas),
        ),
        MenuItem(
            title = "Favoritas",
            selectedIcon = painterResource(id = R.drawable.favoritar_solid),
            unselectedIcon = painterResource(id = R.drawable.favoritar_solid),
        ),
        MenuItem(
            title = "Arquivo",
            selectedIcon = painterResource(id = R.drawable.arquivo),
            unselectedIcon = painterResource(id = R.drawable.arquivo),
        ),
        MenuItem(
            title = "Lixeira",
            selectedIcon = painterResource(id = R.drawable.lixo),
            unselectedIcon = painterResource(id = R.drawable.lixo),
        ),
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            Spacer(modifier = Modifier.height(16.dp))
            items.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = {
                        Text(text = item.title)
                    },
                    selected = index == selectedItemIndex,
                    onClick = {
//                      navController.navigate(item.route)
                        selectedItemIndex = index
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            painter = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title,
                            modifier = tamanhoIcone
                        )
                    },
                    badge = {
                        item.badgeCount?.let {
                            Text(text = item.badgeCount.toString())
                        }
                    },
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
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
                })

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