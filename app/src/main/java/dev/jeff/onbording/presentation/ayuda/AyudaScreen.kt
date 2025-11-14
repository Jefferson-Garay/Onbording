package dev.jeff.onbording.presentation.ayuda

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AyudaScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {

        Text("Centro de Ayuda",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Bienvenido al Centro de Ayuda de TCS. Aquí encontrarás todo lo que necesitas para agilizar tu integración y resolver tus dudas.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(25.dp))

        // --- Chatbot ---
        Text(
            text = "ChatBot",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .clickable { navController.navigate("chat") }
                .padding(vertical = 6.dp)
        )

        Text("Obtén ayuda instantánea escribiendo tus consultas.")

        Spacer(modifier = Modifier.height(20.dp))

        // --- Contactar Supervisor ---
        Text(
            text = "Contactar Supervisor",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .clickable { navController.navigate("supervisor") }
                .padding(vertical = 6.dp)
        )
        Text("¿Necesitas apoyo administrativo o resolver temas laborales?")
        Text("📧  hr@tcs.com")

        Spacer(modifier = Modifier.height(20.dp))

        // --- Soporte IT ---
        Text(
            text = "Soporte IT 24/7",
            style = MaterialTheme.typography.titleMedium
        )
        Text("¿Tienes problemas técnicos?")
        Text("📞  +51 1 234 5678")

        Spacer(modifier = Modifier.height(25.dp))

        // --- Preguntas Frecuentes ---
        Text(
            text = "Preguntas Frecuentes",
            style = MaterialTheme.typography.titleMedium
        )

        val preguntas = listOf(
            "Acceso: Cómo ingresar a la intranet",
            "Onboarding: Información del primer día",
            "Contacto: Cómo ubicar a tu supervisor",
            "Documentación: Dónde encontrar formularios",
            "Recursos: Solicitud de equipamiento",
            "Beneficios: Prestaciones para empleados",
            "Capacitación: Acceso a cursos",
            "Soporte: Problemas técnicos"
        )

        preguntas.forEach { pregunta ->
            Text(
                "• $pregunta",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // --- Recursos útiles ---
        Text(
            text = "Recursos Útiles",
            style = MaterialTheme.typography.titleMedium
        )

        val recursos = listOf(
            "📘 Guía de Onboarding – Manual completo",
            "▶️ Video tutorial del Portal de Empleados",
            "📄 Políticas y Procedimientos",
            "💬 Comunidad de Nuevos Empleados"
        )

        recursos.forEach {
            Text(
                it,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
