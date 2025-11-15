package dev.jeff.onbording.presentation.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AdminHomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { AnalyticsPanel() }
        item { StatCard(icon = Icons.Default.People, title = "Empleados Activos", value = "4 / 5", subtitle = "Total en onboarding") }
        item { StatCard(icon = Icons.Default.Chat, title = "Interacciones Hoy", value = "0", subtitle = "Consultas al asistente") }
        item { StatCard(icon = Icons.Default.TrendingUp, title = "Progreso Promedio", value = "66%", subtitle = "Onboarding completado") }
        item { StatCard(icon = Icons.Default.Timer, title = "Tiempo Promedio", value = "7 días", subtitle = "Duración del proceso") }
        item { ProgressDistributionCard() }
        item { OnboardingStatusCard() }
        item { TopTopicsCard() }
        item { OnboardingEmployeesCard() }
    }
}

@Composable
fun AnalyticsPanel() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF303F9F))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Panel de Analítica", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Gestión y seguimiento de onboarding - Recursos Humanos", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.People, contentDescription = "Empleados")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("5 empleados")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Chat, contentDescription = "Conversaciones")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("0 conversaciones")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Actualizar", tint = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Actualizar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.FileCopy, contentDescription = "Copiar", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Download, contentDescription = "Descargar", tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun StatCard(icon: ImageVector, title: String, value: String, subtitle: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = value, fontSize = 24.sp)
                Text(text = subtitle, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ProgressDistributionCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Distribución de Progreso", fontWeight = FontWeight.Bold)
            Text("Empleados por rango de avance")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Bar(color = Color.Red, heightFraction = 0.5f, label = "26-50%")
                Bar(color = Color.Yellow, heightFraction = 0.5f, label = "")
                Bar(color = Color.Blue, heightFraction = 0.5f, label = "")
                Bar(color = Color.Green, heightFraction = 1.0f, label = "76-100%")
            }
        }
    }
}

@Composable
fun OnboardingStatusCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Estado del Onboarding", fontWeight = FontWeight.Bold)
            Text("Distribución por estado")
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
fun TopTopicsCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Temas Más Consultados al Asistente IA", fontWeight = FontWeight.Bold)
            Text("Top 5 categorías de consultas")
            Spacer(modifier = Modifier.height(16.dp))
            Icon(Icons.Default.Info, contentDescription = "No hay datos")
            Text("No hay datos de consultas disponibles")
        }
    }
}

@Composable
fun OnboardingEmployeesCard() {
    var isExpanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Empleados en Onboarding", fontWeight = FontWeight.Bold)
                    Text("Gestión y seguimiento de nuevos colaboradores")
                }
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = "Expandir")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Buscar por nombre o email...") },
                modifier = Modifier.fillMaxWidth()
            )
            if (isExpanded) {
                EmployeeTable(
                    employees = listOf(
                        Employee("Ana Torres", "ana.torres@tcs.com", "Hace 3h", "Completado", "100%", "18", "15/15"),
                        Employee("Carlos Mendoza", "carlos.mendoza@tcs.com", "Hace 1d", "Pendiente", "20%", "3", "2/10"),
                        Employee("José Rodríguez", "jose.rodriguez@tcs.com", "Hace 2h", "En Progreso", "65%", "12", "8/12"),
                        Employee("Luis Pérez", "luis.perez@tcs.com", "Hace 5h", "En Progreso", "45%", "8", "5/11"),
                        Employee("María García", "maria.garcia@tcs.com", "Hace 1h", "Completado", "100%", "24", "15/15")
                    )
                )
            } else {
                EmployeeTableSimple(
                    employees = listOf(
                        EmployeeSimple("Ana Torres", "ana.torres@tcs.com", "Hace 3h"),
                        EmployeeSimple("Carlos Mendoza", "carlos.mendoza@tcs.com", "Hace 1d"),
                        EmployeeSimple("José Rodríguez", "jose.rodriguez@tcs.com", "Hace 2h"),
                        EmployeeSimple("Luis Pérez", "luis.perez@tcs.com", "Hace 5h"),
                        EmployeeSimple("María García", "maria.garcia@tcs.com", "Hace 1h")
                    )
                )
            }
        }
    }
}

@Composable
fun EmployeeTable(employees: List<Employee>) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            TableCell(text = "Empleado", weight = 2f)
            TableCell(text = "Última Interacción", weight = 1f)
            TableCell(text = "Estado", weight = 1f)
            TableCell(text = "Progreso", weight = 1f)
            TableCell(text = "Mensajes", weight = 1f)
            TableCell(text = "Tareas", weight = 1f)
            TableCell(text = "Acciones", weight = 1f)
        }
        employees.forEach { employee ->
            Row(modifier = Modifier.fillMaxWidth()) {
                TableCell(text = "${employee.name}\n${employee.email}", weight = 2f)
                TableCell(text = employee.lastInteraction, weight = 1f)
                TableCell(text = employee.status, weight = 1f)
                TableCell(text = employee.progress, weight = 1f)
                TableCell(text = employee.messages, weight = 1f)
                TableCell(text = employee.tasks, weight = 1f)
                TableCell(text = "", weight = 1f)
            }
        }
    }
}

@Composable
fun EmployeeTableSimple(employees: List<EmployeeSimple>) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            TableCell(text = "Empleado", weight = 2f)
            TableCell(text = "Última Interacción", weight = 1f)
        }
        employees.forEach { employee ->
            Row(modifier = Modifier.fillMaxWidth()) {
                TableCell(text = "${employee.name}\n${employee.email}", weight = 2f)
                TableCell(text = employee.lastInteraction, weight = 1f)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(text: String, weight: Float) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun Bar(color: Color, heightFraction: Float, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(heightFraction)
                .background(color)
        )
        Text(text = label)
    }
}

data class Employee(val name: String, val email: String, val lastInteraction: String, val status: String, val progress: String, val messages: String, val tasks: String)
data class EmployeeSimple(val name: String, val email: String, val lastInteraction: String)
