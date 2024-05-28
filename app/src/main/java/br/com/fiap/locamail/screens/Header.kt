package br.com.fiap.locamail.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro
import kotlinx.coroutines.launch
import retrofit2.http.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    onNavigationIconClick: () -> Unit
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
            modifier = Modifier
                .size(35.dp)
                .padding(top = 10.dp)
                .clickable(onClick = {
                    onNavigationIconClick()
                }),
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
            modifier = Modifier
                .size(40.dp)
        )
    }
}