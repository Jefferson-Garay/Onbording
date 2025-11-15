package dev.jeff.onbording.presentation.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AdminAyudaScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HelpCenterHeaderCard() }
        item { InfoCard(icon = Icons.Default.Email, title = "Contactar RRHH", subtitle = "hr@tcs.com") }
        item { InfoCard(icon = Icons.Default.Phone, title = "Soporte IT 24/7", subtitle = "+51 1 234 5678") }
        item { FaqCard() }
        item { UsefulResourcesCard() }
        item { SupportFooterCard() }
    }
}

@Composable
fun HelpCenterHeaderCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.HelpOutline, contentDescription = "Centro de Ayuda")
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Centro de Ayuda", fontWeight = FontWeight.Bold)
                Text(text = "Encuentra respuestas a tus preguntas y recursos útiles")
            }
        }
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, subtitle: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = subtitle)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqCard() {
    var expandedItem by remember { mutableStateOf<String?>(null) }
    val faqs = listOf(
        Triple(
            "Acceso",
            "¿Cómo accedo a la intranet de TCS?",
            "Puedes acceder a la intranet desde el asistente virtual escribiendo 'intranet' o a través del Portal de Empleados. Usa tus credenciales corporativas para iniciar sesión."
        ),
        Triple(
            "Onboarding",
            "¿Cuándo es mi primer día de trabajo?",
            "Tu fecha de inicio está indicada en la sección 'Mi Información'. También recibirás un correo de confirmación con todos los detalles antes de tu ingreso."
        ),
        Triple(
            "Contacto",
            "¿Cómo contacto a mi supervisor?",
            "Puedes ver toda la información de contacto de tu supervisor en la sección 'Mi Supervisor'. Allí encontrarás su correo, teléfono y horario de atención."
        ),
        Triple(
            "Documentación",
            "¿Dónde encuentro los formularios que necesito?",
            "Todos los formularios están disponibles a través del asistente virtual. Escribe 'formularios' para ver la lista completa de documentos disponibles."
        ),
        Triple(
            "Recursos",
            "¿Cómo solicito equipamiento?",
            "Utiliza el Formulario de Solicitud de Equipamiento disponible en el asistente virtual. Tu solicitud será procesada por el departamento de IT."
        ),
        Triple(
            "Beneficios",
            "¿Qué beneficios tengo como empleado de TCS?",
            "TCS ofrece seguro médico completo, seguro de vida, plan de pensiones, días de vacaciones, capacitación continua y programas de bienestar. Consulta más detalles en Recursos Humanos."
        ),
        Triple(
            "Capacitación",
            "¿Cómo accedo a los cursos de capacitación?",
            "Puedes acceder a la plataforma de e-learning a través del asistente virtual escribiendo 'capacitación' o 'cursos'. Allí encontrarás todo el catálogo de formación disponible."
        ),
        Triple(
            "Soporte",
            "¿Qué hago si tengo problemas técnicos?",
            "Contacta a la Mesa de Ayuda IT disponible 24/7. Puedes encontrar el enlace en el asistente virtual escribiendo 'soporte' o 'ayuda técnica'."
        )
    )

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Preguntas Frecuentes", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Buscar preguntas...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            faqs.forEach { (category, question, answer) ->
                ExpandableFaqItem(
                    category = category,
                    question = question,
                    answer = answer,
                    isExpanded = expandedItem == category,
                    onToggle = { expandedItem = if (expandedItem == category) null else category }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableFaqItem(
    category: String,
    question: String,
    answer: String,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggle() }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AssistChip(
                    onClick = { /* Nothing */ },
                    label = { Text(category) },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = question)
            }
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Colapsar" else "Expandir"
            )
        }
        if (isExpanded) {
            Text(
                text = answer,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
        Divider()
    }
}

@Composable
fun UsefulResourcesCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recursos Útiles", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            ResourceItem(
                icon = Icons.Default.Book,
                title = "Guía de Onboarding",
                subtitle = "Manual completo para nuevos empleados"
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ResourceItem(
                icon = Icons.Default.Videocam,
                title = "Video Tutorial: Portal de Empleados",
                subtitle = "Aprende a navegar por el portal en 5 minutos"
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ResourceItem(
                icon = Icons.Default.FileCopy,
                title = "Políticas y Procedimientos",
                subtitle = "Documentación oficial de TCS"
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            ResourceItem(
                icon = Icons.Default.Group,
                title = "Comunidad de Nuevos Empleados",
                subtitle = "Conéctate con otros nuevos miembros del equipo"
            )
        }
    }
}

@Composable
fun ResourceItem(icon: ImageVector, title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = subtitle)
        }
    }
}

@Composable
fun SupportFooterCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.ContactSupport, contentDescription = "¿No encontraste lo que buscabas?")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "¿No encontraste lo que buscabas?", fontWeight = FontWeight.Bold)
            Text(text = "Nuestro equipo de soporte está aquí para ayudarte")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO */ }) {
                Text("Iniciar chat de soporte")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { /* TODO */ }) {
                Text("Enviar correo")
            }
        }
    }
}
