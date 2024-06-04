package br.com.fiap.locamail.model

import androidx.compose.ui.graphics.painter.Painter

data class MenuItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val badgeCount: Int? = null,
    val path: String
)
