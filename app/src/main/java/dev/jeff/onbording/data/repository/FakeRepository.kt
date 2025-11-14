package dev.jeff.onbording.data.repository

import dev.jeff.onbording.data.model.*

object FakeRepository {
    fun getUsuarioActual(): UsuarioModel {
        val supervisor = SupervisorModel(
            idSupervisor = 1,
            nombre = "Ana Torres",
            correo = "ana.torres@tcs.com",
            telefono = "999888777",
            cargo = "Gerente de Proyecto"
        )
        return UsuarioModel(
            idUsuario = 1,
            nombre = "Jose",
            apellido = "Rodriguez",
            correo = "jose.rodriguez@tcs.com",
            telefono = "987654321",
            rol = "Especialista",
            supervisor = supervisor,
            fechaInicio = "2025-11-10",
            estado = "activo"
        )
    }

    fun getActividades(): List<ActividadModel> = listOf(
        ActividadModel("1", "Inducción General", "Conoce los beneficios de la empresa", "2025-11-12", "presencial", "Alta", "pendiente"),
        ActividadModel("2", "Capacitación Legal", "Privacidad y protección de datos", "2025-11-14", "virtual", "Media", "pendiente")
    )
}
