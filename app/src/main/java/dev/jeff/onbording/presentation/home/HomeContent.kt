package dev.jeff.onbording.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { WelcomeCard() }
        item { ProgressCard() }
        item { NextStepsList() }
        item { ActionCards(navController) }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF303F9F))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "¡BIENVENIDO A TCS, José Rodríguez!",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Estamos emocionados de tenerte en nuestro equipo. Aquí podrás seguir tu proceso de onboarding.",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ProgressCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = "Progreso del Onboarding"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Progreso del Onboarding", fontWeight = FontWeight.Bold)
                }
                Text(text = "0 de 3 actividades completadas")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = 0f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "0% completado", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun NextStepsList() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Próximos Pasos")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Próximos Pasos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                NextStepItem(
                    title = "Completar formularios de RRHH",
                    date = "vie, 14 nov.  09:00",
                    description = "Llenar y firmar los documentos de contratación"
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                NextStepItem(
                    title = "Sesión de orientación general",
                    date = "sáb, 15 nov.  10:00",
                    description = "Introducción a la empresa y cultura organizacional"
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                NextStepItem(
                    title = "Configuración de equipos y cuentas",
                    date = "dom, 16 nov.  14:00",
                    description = "Obtener laptop, credenciales y acceso a sistemas"
                )
            }
        }
    }
}

@Composable
fun NextStepItem(title: String, date: String, description: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(
            imageVector = Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = date, fontSize = 12.sp, color = Color.Gray)
            Text(text = description, fontSize = 14.sp)
        }
    }
}

@Composable
fun ActionCards(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Chat, contentDescription = "¿Tienes preguntas?")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "¿Tienes preguntas?", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Pregunta a nuestro asistente virtual sobre tu onboarding", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("chat") }) {
                    Text(text = "Asistente Virtual")
                }
            }
        }
        Card(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Conoce a tu supervisor"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Conoce a tu supervisor", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Información de contacto y detalles de tu supervisor", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(onClick = { navController.navigate("supervisor") }) {
                    Text(text = "Ver Supervisor")
                }
            }
        }
    }
}
