package dev.jeff.onbording.data.model

data class SupervisorModel(
    val idSupervisor: Int,
    val nombre: String,
    val correo: String,
    val telefono: String?,
    val cargo: String?
)

data class UsuarioModel(
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String?,
    val rol: String,
    val supervisor: SupervisorModel?,
    val fechaInicio: String,
    val estado: String
)
