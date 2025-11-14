package dev.jeff.onbording.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

// --- Datos del menú ---
private data class DrawerOption(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val drawerOptions = listOf(
    DrawerOption("home", "Inicio", Icons.Default.Home),
    DrawerOption("chat", "Chatbot", Icons.Default.Chat),
    DrawerOption("actividades", "Actividades", Icons.Default.Event),
    DrawerOption("supervisor", "Supervisor", Icons.Default.Person),
    DrawerOption("mi_informacion", "Mi Información", Icons.Default.AccountCircle),
    DrawerOption("ayuda", "Ayuda", Icons.Default.Help),
    DrawerOption("config", "Configuración", Icons.Default.Settings)
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

                // Generar items automáticos con íconos
                drawerOptions.forEach { option ->
                    DrawerItem(
                        text = option.label,
                        icon = option.icon
                    ) {
                        if (option.route == "home") {
                            navController.navigate("home") {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        } else {
                            navController.navigate(option.route) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
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
fun DrawerItem(
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
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
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
