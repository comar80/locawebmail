package br.com.fiap.locamail.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.model.MenuItem
import kotlinx.coroutines.launch

@Composable
fun MenuLateral(navController: NavController) {

    val tamanhoIcone = Modifier
        .size(35.dp)

    val items = listOf(
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
        ),
        MenuItem(
            title = "Sair",
            selectedIcon = painterResource(id = R.drawable.sair),
            unselectedIcon = painterResource(id = R.drawable.sair),
            path = "login"
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    navController.navigate(item.path)
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
}