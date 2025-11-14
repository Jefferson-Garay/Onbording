package dev.jeff.onbording.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
    navController: NavHostController,
    content: @Composable () -> Unit
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

                DrawerItem("Inicio") {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                        launchSingleTop = true
                    }
                }

                DrawerItem("Chatbot") { navController.navigate("chat") }
                DrawerItem("Actividades") { navController.navigate("actividades") }
                DrawerItem("Supervisor") { navController.navigate("supervisor") }
                DrawerItem("Mi Información") { navController.navigate("mi_informacion") }
            }
        }
    ) {
        Scaffold(
            topBar = {
                HomeTopBar(
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                content()
            }
        }
    }
}
@Composable
fun DrawerItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
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