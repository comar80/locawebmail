package br.com.fiap.locamail.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

@Composable
fun SwitchTheme(isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.padding(start = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = isDarkMode,
            onCheckedChange = { newCheckedState ->
                onThemeChange(newCheckedState)
            },
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = "Modo Escuro",
            textAlign = TextAlign.Center)
    }
}
