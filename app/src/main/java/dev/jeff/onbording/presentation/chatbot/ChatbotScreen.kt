package dev.jeff.onbording.presentation.chatbot


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        "Sistema: Hola, soy tu asistente de onboarding. ¿En qué puedo ayudarte?"
    ) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Chatbot de Onboarding") }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Atrás") }
        })
    }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(12.dp)
            .fillMaxSize()) {

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messages) { msg ->
                    Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                        Text(msg, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text("Escribe tu pregunta...") },
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = {
                    if (input.isNotBlank()) {
                        messages.add("Tú: $input")
                        // respuesta simulada
                        messages.add("Sistema: Respuesta simulada a \"$input\" (usa FakeRepository en el futuro)")
                        input = ""
                    }
                }) {
                    Text("Enviar")
                }
            }
        }
    }
}
