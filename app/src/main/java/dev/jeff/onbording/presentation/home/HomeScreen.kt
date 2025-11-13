package dev.jeff.onbording.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jeff.onbording.data.repository.FakeRepository
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenChat: () -> Unit) {
    val usuario = FakeRepository.getUsuarioActual()
    val actividades = FakeRepository.getActividades()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Bienvenido, ${usuario.nombre}") })
    }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
            .fillMaxSize()) {

            Text(text = "Supervisor: ${usuario.supervisor?.nombre ?: "Sin asignar"}")
            Text(text = "Correo: ${usuario.supervisor?.correo ?: "-"}")
            Text(text = "Fecha de inicio: ${usuario.fechaInicio}")
            Spacer(modifier = Modifier.height(12.dp))

            Text("Próximas actividades:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            actividades.forEach { act ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(act.titulo, style = MaterialTheme.typography.titleSmall)
                        Text(act.descripcion, style = MaterialTheme.typography.bodySmall)
                        Text("Fecha: ${act.fechaProgramada}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onOpenChat, modifier = Modifier.fillMaxWidth()) {
                Text("Abrir Chatbot")
            }
        }
    }
}
