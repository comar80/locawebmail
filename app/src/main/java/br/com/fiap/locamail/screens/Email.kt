package br.com.fiap.locamail.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locamail.R
import br.com.fiap.locamail.database.repository.CaixaRepository
import br.com.fiap.locamail.database.repository.EmailRepository
import br.com.fiap.locamail.ui.theme.SfPro

@Composable
fun EmailScreen(navController: NavController, onCalendarIconClick: () -> Unit, titulo: String, nome: String, horario: String, conteudo: String, emailId: String) {

    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val caixaRepository = CaixaRepository(context)
    val emailRepository = EmailRepository(context)
    val listaCaixas = caixaRepository.getCaixas()

    Box {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            verticalArrangement = Arrangement.SpaceBetween
            ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 5.dp),
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
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.lixo),
                        contentDescription = "lixeira",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp)
                            .clickable (
                                onClick = {
                                    emailRepository.moverEmail(4, emailId.toLong())
                                    navController.popBackStack()
                                    Toast.makeText(context, "Mensagem excluída", Toast.LENGTH_LONG).show()
                                }
                            ),
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "calendario",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp, start = 10.dp)
                            .clickable {
                                onCalendarIconClick()
                            },
                        tint = colorResource(id = R.color.preto_locaweb)
                    )
                    Box {
                        Icon(
                            painter = painterResource(id = R.drawable.opcoes),
                            contentDescription = "calendario",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(top = 10.dp, start = 10.dp)
                                .clickable(
                                    onClick = { expanded = true }
                                ),
                            tint = colorResource(id = R.color.preto_locaweb)
                        )
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            DropdownMenuItem({ Text(text = "Mover para:") }, onClick = { /*TODO*/ }, enabled = false)
                            listaCaixas.forEach{
                                DropdownMenuItem({ Text(text = it.nomeCaixa) },
                                    onClick = {
                                        emailRepository.moverEmail(it.caixaId, emailId.toLong())
                                        navController.popBackStack()
                                        Toast.makeText(context, "Mensagem movida para ${it.nomeCaixa}", Toast.LENGTH_LONG).show()
                                    })
                            }
                        }
                    }

                }

            }

            Divider(thickness = 1.dp)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = titulo,
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
                .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp)
            ) {
                Text(
                    text = nome,
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
                Text(
                    text = horario,
                    fontFamily = SfPro,
                    color = colorResource(id = R.color.preto_locaweb)
                )
            }

            Divider(thickness = 1.dp)

            Box(modifier = Modifier
                .height(450.dp)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                .verticalScroll(rememberScrollState())){
                Text(
                    text = conteudo,
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
                    onClick = {
                        navController.navigate("responder/${titulo}/${nome}/${conteudo}")
                    },
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
                    onClick = {
                        navController.navigate("encaminhar/${titulo}/${conteudo}")
                    },
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