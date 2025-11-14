package dev.jeff.onbording.presentation.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jeff.onbording.R

@Composable
fun ChatbotScreen(onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            "Sistema: Hola, soy tu asistente de onboarding. ¿En qué puedo ayudarte?"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (messages.size <= 1) {
            WelcomeChat(onSuggestionClick = { suggestion ->
                messages.add("Tú: $suggestion")
                messages.add("Sistema: Respuesta simulada a \"$suggestion\"")
            })
        } else {
            ChatHistory(messages = messages, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        ChatInput(
            value = input,
            onValueChange = { input = it },
            onSend = {
                if (input.isNotBlank()) {
                    messages.add("Tú: $input")
                    messages.add("Sistema: Respuesta simulada a \"$input\"")
                    input = ""
                }
            }
        )
    }
}

@Composable
fun WelcomeChat(onSuggestionClick: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.chatbot_icon),
            contentDescription = "Chatbot Icon",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¡Hola! Soy tu asistente de onboarding",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Puedo ayudarte a encontrar enlaces y recursos importantes para tu incorporación. Pregúntame por: intranet, políticas, formularios, capacitación o ver todos.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Preguntas sugeridas:")
        Spacer(modifier = Modifier.height(8.dp))
        SuggestionChip("¿Cuáles son las políticas de TCS?", onSuggestionClick)
        SuggestionChip("Muéstrame los formularios disponibles", onSuggestionClick)
        SuggestionChip("Necesito acceso a la intranet", onSuggestionClick)
    }
}

@Composable
fun SuggestionChip(text: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(text) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F4FF))
    ) {
        Text(text = text, color = Color.DarkGray)
    }
}

@Composable
fun ChatHistory(messages: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) { msg ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(msg, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun ChatInput(value: String, onValueChange: (String) -> Unit, onSend: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Escribe tu pregunta aquí...") },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onSend) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Enviar")
        }
    }
}
