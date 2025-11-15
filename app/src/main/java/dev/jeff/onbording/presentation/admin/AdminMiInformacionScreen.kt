package dev.jeff.onbording.presentation.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminMiInformacionScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF303F9F))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AdminPanelSettings,
                        contentDescription = "Información del administrador",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Información del administrador",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        item {
            InfoRow(icon = Icons.Default.Person, label = "Nombre", value = "Administrador TCS")
        }
        item {
            InfoRow(
                icon = Icons.Default.DateRange,
                label = "Fecha de inicio",
                value = "20 de noviembre de 2025"
            )
        }
        item {
            InfoRow(
                icon = Icons.Default.SupervisorAccount,
                label = "Supervisor",
                value = "Dirección General"
            )
        }
        item {
            InfoRow(
                icon = Icons.Default.Email,
                label = "Correo del supervisor",
                value = "direccion@tcs.com"
            )
        }
        item {
            InfoRow(icon = Icons.Default.Work, label = "Rol", value = "admin")
        }
        item {
            NextSteps()
        }
        item {
            UsefulUrls()
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp)
        }
    }
}

@Composable
fun NextSteps() {
    Column {
        Text(text = "Próximos pasos", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text("1.")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Panel de administración disponible")
        }
        Row {
            Text("2.")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Gestión de usuarios y configuración")
        }
    }
}

@Composable
fun UsefulUrls() {
    Column {
        Text(text = "URLs útiles", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("https://portal.tcs.com")
        Text("https://admin.tcs.com")
    }
}
