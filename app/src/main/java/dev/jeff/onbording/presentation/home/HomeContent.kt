package dev.jeff.onbording.presentation.home


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeContent() {
    // Aquí irá lo que quieras que se vea dentro del Home.
    // Por ahora lo dejamos mínimo.
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Bienvenido al Home")
    }
}
