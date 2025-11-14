package dev.jeff.onbording.presentation.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Badge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

// --- Datos del menú (puedes expandir más tarde) ---
private data class DrawerOption(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val drawerOptions = listOf(
    DrawerOption(route = "home", label = "Inicio", icon = Icons.Default.Home),
    DrawerOption(route = "chat", label = "Chatbot", icon = Icons.Default.Chat),
    DrawerOption(route = "actividades", label = "Actividades", icon = Icons.Default.Event),
    DrawerOption(route = "supervisor", label = "Supervisor", icon = Icons.Default.Person),
    DrawerOption(route = "miinfo", label = "Mi Información", icon = Icons.Default.AccountCircle),
    DrawerOption(route = "ayuda", label = "Ayuda", icon = Icons.Default.Help),
    DrawerOption(route = "config", label = "Configuración", icon = Icons.Default.Settings)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWithDrawer(
    navController: NavController,
    userInitials: String = "JR",
    notificationCount: Int = 3,
    content: @Composable () -> Unit // aquí irá tu HomeScreen content o lo que quieras mostrar
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open) // ABIERTO por defecto
    val scope = rememberCoroutineScope()

    // Estado del item seleccionado
    var selectedRoute by remember { mutableStateOf("home") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Drawer (sheet)
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(Color(0xFF072B4A)) // azul oscuro
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    // --- Icono Cerrar (X) arriba ---
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            scope.launch { drawerState.close() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar menú",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // --- Opciones del menú ---
                    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
                        drawerOptions.forEach { opt ->
                            val selected = opt.route == selectedRoute
                            NavigationDrawerItem(
                                label = { Text(opt.label, color = Color.White) },
                                selected = selected,
                                onClick = {
                                    selectedRoute = opt.route
                                    scope.launch {
                                        drawerState.close()
                                        // pequeña demora para que cierre antes de navegar
                                        kotlinx.coroutines.delay(150)
                                        navController.navigate(opt.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = opt.icon,
                                        contentDescription = opt.label,
                                        tint = if (selected) Color(0xFFBFE6FF) else Color.White
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color(0xFF0E4A72), // azul más claro al seleccionar
                                    unselectedContainerColor = Color.Transparent,
                                    selectedTextColor = Color.White,
                                    unselectedTextColor = Color.White
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // --- Pie del Drawer (opcional) ---
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Versión 1.0", color = Color(0xFFBFD7EA))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    ) {
        // --- Top bar + contenido principal (fuera del drawer) ---
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Logo simulado
                        Surface(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            color = Color.White.copy(alpha = 0.12f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("LOGO", color = Color.White, modifier = Modifier.padding(4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(text = "Empresa X", color = Color.White)
                    }
                },
                actions = {
                    // Icono iniciales del usuario
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF0F8ED1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(userInitials, color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Notificaciones con contador
                    Box(modifier = Modifier.padding(horizontal = 6.dp)) {
                        IconButton(onClick = { /* navegar a notificaciones */ }) {
                            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notificaciones", tint = Color.White)
                        }
                        if (notificationCount > 0) {
                            Badge(
                                modifier = Modifier
                                    .offset(x = (-6).dp, y = 4.dp)
                            ) {
                                Text(text = notificationCount.toString())
                            }
                        }
                    }

                    IconButton(onClick = { /* sincronizar */ }) {
                        Icon(imageVector = Icons.Default.Autorenew, contentDescription = "Sincronizar", tint = Color.White)
                    }

                    IconButton(onClick = { /* compartir */ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir", tint = Color.White)
                    }

                    Spacer(modifier = Modifier.width(6.dp))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0A2A43)), // coincide con drawer
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2A43)
                )
            )

            // --- Contenido principal proporcionado por `content` ---
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)) {
                content()
            }
        }
    }
}

@Composable
fun DrawerItem(text: String) {
    Text(
        text = text,
        color = Color.White,
        modifier = Modifier.padding(16.dp)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text("Inicio", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0A2A43)
        )
    )
}