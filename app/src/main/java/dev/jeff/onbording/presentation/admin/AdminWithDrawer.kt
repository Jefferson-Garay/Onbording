package dev.jeff.onbording.presentation.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jeff.onbording.presentation.home.HomeTopBar
import kotlinx.coroutines.launch

private data class AdminDrawerOption(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val isHeader: Boolean = false
)

private val adminDrawerOptions = listOf(
    AdminDrawerOption("admin_home", "Inicio", Icons.Default.Home),
    AdminDrawerOption("admin_mi_informacion", "Mi Información", Icons.Default.AccountCircle),
    AdminDrawerOption("chat_history", "Historial de Chat", Icons.Default.History),
    AdminDrawerOption("admin_ayuda", "Ayuda", Icons.Default.Help),
    AdminDrawerOption("admin_configuracion", "Configuración", Icons.Default.Settings),
    AdminDrawerOption("", "Panel Administrativo", Icons.Default.AdminPanelSettings, isHeader = true),
    AdminDrawerOption("proactive_messages", "Mensajes Proactivos", Icons.Default.Send),
    AdminDrawerOption("full_history", "Historial Completo", Icons.Default.List),
    AdminDrawerOption("user_management", "Gestión de Usuarios", Icons.Default.People)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminWithDrawer(
    navController: NavHostController,
    title: String,
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
    content: @Composable (NavHostController) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxHeight(),
                drawerContainerColor = Color(0xFF0D1B2A)
            ) {
                Column(modifier = Modifier.fillMaxHeight()) {
                    Column(modifier = Modifier.weight(1f)) {
                        adminDrawerOptions.forEach { option ->
                            if (option.isHeader) {
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                Text(
                                    text = option.label,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            } else {
                                AdminDrawerItem(
                                    text = option.label,
                                    icon = option.icon
                                ) {
                                    navController.navigate(option.route) {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }
                    }
                    AdminDrawerItem(
                        text = "Cerrar sesión",
                        icon = Icons.Default.ExitToApp,
                        onClick = {
                            navController.navigate("login") {
                                popUpTo(0) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                HomeTopBar(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    toggleTheme = toggleTheme,
                    isAdmin = true
                )
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                content(navController)
            }
        }
    }
}

@Composable
fun AdminDrawerItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp, horizontal = 20.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = text,
            color = Color.White
        )
    }
}
