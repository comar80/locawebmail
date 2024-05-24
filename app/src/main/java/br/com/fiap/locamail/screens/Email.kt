package br.com.fiap.locamail.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.ui.theme.SfPro

@Composable
fun EmailScreen(navController: NavController) {

    Box {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            verticalArrangement = Arrangement.SpaceBetween
            ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.voltar),
                    contentDescription = "voltar",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 10.dp),
                    tint = colorResource(id = R.color.preto_locaweb)
                )
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arquivo),
                        contentDescription = "arquivar",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.lixo),
                        contentDescription = "lixeira",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.opcoes),
                        contentDescription = "mais opcoes",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                }

            }

            Divider(thickness = 1.dp)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Titulo do Email",
                    fontSize = 30.sp,
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
                Icon(
                    painter = painterResource(id = R.drawable.favoritar),
                    contentDescription = "favoritar",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 10.dp, start = 10.dp),
                    tint = colorResource(id = R.color.preto_locaweb)
                )
            }

            Divider(thickness = 1.dp)

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "Nome",
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
                Text(
                    text = "Horario",
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
            }

            Divider(thickness = 1.dp)

            Box(modifier = Modifier
                .height(500.dp)
                .padding(start = 10.dp, end = 10.dp)){
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "Aenean sit amet ullamcorper nunc. Proin quis condimentum massa. " +
                            "Nullam eget efficitur lectus. Nam tincidunt eros eget enim finibus, " +
                            "eget dapibus mi sodales. Vestibulum elementum, magna vel viverra commodo, " +
                            "dui justo commodo nisi, id placerat massa metus quis diam. Aliquam diam tellus," +
                            " porttitor et odio vel, pretium eleifend quam. Ut sed suscipit nulla," +
                            " at consequat libero. Ut rhoncus diam ut lectus commodo," +
                            " vitae suscipit dolor convallis. Cras pretium elementum ultrices. " +
                            "Mauris semper non neque quis ultricies. Vivamus ipsum nunc, lacinia vitae ex sed," +
                            " luctus dapibus augue. Mauris tristique lorem eget est rhoncus lacinia.",
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(id = R.color.azul_locaweb),
                    ),
                    border = BorderStroke(1.dp, colorResource(id = R.color.azul_locaweb))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.responder),
                        contentDescription = "responder",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(text = "Responder", fontFamily = SfPro)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(id = R.color.preto_locaweb),
                    ),
                    border = BorderStroke(1.dp, colorResource(id = R.color.preto_locaweb))
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.encaminhar),
                        contentDescription = "encaminhar",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(text = "Encaminhar", fontFamily = SfPro)
                }
            }
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun EmailScreenPV() {
    val navController = rememberNavController()
    EmailScreen(navController = navController)
}