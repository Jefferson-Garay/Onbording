package dev.jeff.onbording.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.jeff.onbording.R
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
                    DrawerItem(
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
                    isAdmin = false
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
fun HomeTopBar(
    onMenuClick: () -> Unit,
    navController: NavHostController,
    isDarkTheme: Boolean,
    toggleTheme: () -> Unit,
    isAdmin: Boolean
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "TCS Logo",
                    modifier = Modifier.size(40.dp)
                )
                Text("TCS", color = Color.White)
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
                    .clickable {
                        if (isAdmin) {
                            navController.navigate("admin_mi_informacion")
                        } else {
                            navController.navigate("mi_informacion")
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("JR", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            BadgedBox(
                badge = { Badge { Text("7") } }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = toggleTheme) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Theme",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                navController.navigate("login") {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0A2A43)
        )
    )
}
