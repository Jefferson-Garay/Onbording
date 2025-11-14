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
import androidx.navigation.compose.rememberNavController
import dev.jeff.onbording.presentation.actividades.ActividadesScreen
import dev.jeff.onbording.presentation.chatbot.ChatbotScreen
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
fun HomeWithDrawer() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // NavController interno del Home
    val internalNav = rememberNavController()
    var selectedRoute by remember { mutableStateOf("homeContent") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(280.dp)
                    .background(Color(0xFF072B4A))
            ) {

                Spacer(Modifier.height(20.dp))

                drawerOptions.forEach { opt ->
                    NavigationDrawerItem(
                        label = { Text(opt.label, color = Color.White) },
                        selected = selectedRoute == opt.route,
                        onClick = {
                            selectedRoute = opt.route
                            scope.launch {
                                drawerState.close()
                                kotlinx.coroutines.delay(160)
                                internalNav.navigate(opt.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = opt.icon,
                                contentDescription = opt.label,
                                tint = Color.White
                            )
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // TopBar
            TopAppBar(
                title = { Text("Empresa X", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2A43)
                )
            )

            // CONTENIDO PRINCIPAL
            Box(Modifier.fillMaxSize()) {

                NavHost(
                    navController = internalNav,
                    startDestination = "homeContent"
                ) {

                    composable("homeContent") { HomeContent() }
                    composable("chat") { ChatbotScreen(onBack = { internalNav.navigate("homeContent") }) }
                    composable("actividades") { ActividadesScreen() }
                }
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