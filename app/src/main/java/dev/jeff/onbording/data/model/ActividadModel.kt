package dev.jeff.onbording.data.model

data class ActividadModel(
    val idActividad: String,
    val titulo: String,
    val descripcion: String,
    val fechaProgramada: String,
    val tipo: String,
    val prioridad: String,
    val estado: String
)
