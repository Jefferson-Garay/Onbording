package dev.jeff.onbording.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jeff.onbording.presentation.admin.*
import dev.jeff.onbording.presentation.auth.LoginScreen
import dev.jeff.onbording.presentation.chatbot.ChatbotScreen
import dev.jeff.onbording.presentation.home.HomeWithDrawer
import dev.jeff.onbording.presentation.actividades.ActividadesScreen
import dev.jeff.onbording.presentation.ayuda.AyudaScreen
import dev.jeff.onbording.presentation.config.ConfiguracionScreen
import dev.jeff.onbording.presentation.home.HomeContent
import dev.jeff.onbording.presentation.mi_informacion.MiInformacionScreen
import dev.jeff.onbording.presentation.supervisor.SupervisorScreen

@Composable
fun AppNavGraph(startDestination: String = "login", isDarkTheme: Boolean, toggleTheme: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onEmployeeLogin = { navController.navigate("home") },
                onAdminLogin = { navController.navigate("admin_home") }
            )
        }
        composable("home") {
            HomeWithDrawer(
                navController,
                title = "Inicio",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                HomeContent(navController)
            }
        }
        composable("admin_home") {
            AdminWithDrawer(
                navController,
                title = "Admin Home",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                AdminHomeScreen(navController)
            }
        }
        composable("chat") {
            HomeWithDrawer(
                navController,
                title = "Chatbot",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                ChatbotScreen(onBack = { navController.popBackStack() })
            }
        }
        composable("actividades") {
            HomeWithDrawer(
                navController,
                title = "Actividades",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                ActividadesScreen()
            }
        }
        composable("supervisor") {
            HomeWithDrawer(
                navController,
                title = "Supervisor",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                SupervisorScreen()
            }
        }
        composable("mi_informacion") {
            HomeWithDrawer(
                navController,
                title = "Mi Información",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                MiInformacionScreen()
            }
        }
        composable("ayuda") {
            HomeWithDrawer(
                navController,
                title = "Ayuda",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                AyudaScreen(navController)
            }
        }
        composable("config") {
            HomeWithDrawer(
                navController,
                title = "Configuración",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                ConfiguracionScreen()
            }
        }
        composable("chat_history") {
            AdminWithDrawer(
                navController,
                title = "Historial de Chat",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                ChatHistoryScreen()
            }
        }
        composable("proactive_messages") {
            AdminWithDrawer(
                navController,
                title = "Mensajes Proactivos",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                ProactiveMessagesScreen()
            }
        }
        composable("full_history") {
            AdminWithDrawer(
                navController,
                title = "Historial Completo",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                FullHistoryScreen()
            }
        }
        composable("user_management") {
            AdminWithDrawer(
                navController,
                title = "Gestión de Usuarios",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                UserManagementScreen()
            }
        }
        composable("admin_mi_informacion") {
            AdminWithDrawer(
                navController,
                title = "Mi Información (Admin)",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                AdminMiInformacionScreen()
            }
        }
        composable("admin_ayuda") {
            AdminWithDrawer(
                navController,
                title = "Ayuda (Admin)",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                AdminAyudaScreen()
            }
        }
        composable("admin_configuracion") {
            AdminWithDrawer(
                navController,
                title = "Configuración (Admin)",
                isDarkTheme = isDarkTheme,
                toggleTheme = toggleTheme
            ) {
                AdminConfiguracionScreen()
            }
        }
    }
}
