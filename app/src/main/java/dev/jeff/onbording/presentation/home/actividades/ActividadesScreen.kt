package dev.jeff.onbording.presentation.home.actividades

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jeff.onbording.data.model.ActividadModel

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ActividadesScreen() {

    // ============================
    // SIMULACIÓN DE DATOS (Mock)
    // ============================
    val actividadesIniciales = remember {
        mutableStateListOf(
            ActividadModel(
                idActividad = "1",
                titulo = "Reunión de seguimiento",
                descripcion = "Revisión semanal con el supervisor.",
                fechaProgramada = "2025-11-14 09:00",
                tipo = "Reunión",
                prioridad = "Alta",
                estado = "Pendiente"
            ),
            ActividadModel(
                idActividad = "2",
                titulo = "Entrega de reporte",
                descripcion = "Enviar reporte mensual de avances.",
                fechaProgramada = "2025-11-16 14:00",
                tipo = "Tarea",
                prioridad = "Media",
                estado = "Pendiente"
            ),
            ActividadModel(
                idActividad = "3",
                titulo = "Capacitación virtual",
                descripcion = "Curso obligatorio de seguridad.",
                fechaProgramada = "2025-11-18 10:30",
                tipo = "Capacitación",
                prioridad = "Alta",
                estado = "Completada"
            )
        )
    }

    val completadas = actividadesIniciales.count { it.estado == "Completada" }
    val total = actividadesIniciales.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Título e indicador
        Text(
            text = "Actividades",
            fontSize = 28.sp,
            color = Color.Black
        )

        Text(
            text = "$completadas de $total completadas",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(actividadesIniciales) { actividad ->

                ActividadCard(
                    actividad = actividad,
                    onToggleEstado = {
                        val index = actividadesIniciales.indexOf(actividad)
                        val nuevoEstado = if (actividad.estado == "Pendiente") "Completada" else "Pendiente"

                        actividadesIniciales[index] = actividad.copy(estado = nuevoEstado)
                    }
                )
            }
        }
    }
}

@Composable
fun ActividadCard(
    actividad: ActividadModel,
    onToggleEstado: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = if (actividad.estado == "Completada")
                        Icons.Default.CheckCircle
                    else
                        Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onToggleEstado() },
                    tint = if (actividad.estado == "Completada") Color(0xFF4CAF50) else Color.Gray
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = actividad.titulo,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fecha formateada
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = actividad.fechaProgramada,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = actividad.descripcion,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
    }
}
