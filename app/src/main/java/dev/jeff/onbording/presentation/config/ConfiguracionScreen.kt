package dev.jeff.onbording.presentation.config

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfiguracionScreen() {

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            "Configuración",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Aquí podrás editar tus preferencias más adelante.")
    }
}
