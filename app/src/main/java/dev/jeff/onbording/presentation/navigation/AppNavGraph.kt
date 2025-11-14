package dev.jeff.onbording.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jeff.onbording.presentation.auth.LoginScreen
import dev.jeff.onbording.presentation.home.ChatbotScreen
import dev.jeff.onbording.presentation.home.HomeContent

import dev.jeff.onbording.presentation.home.HomeWithDrawer
import dev.jeff.onbording.presentation.home.actividades.ActividadesScreen

@Composable
fun AppNavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(onLogin = { navController.navigate("home") }) }
        composable("home") {
            HomeWithDrawer(navController = navController) {
                HomeContent()
            }
        }
        composable("chat") { ChatbotScreen(onBack = { navController.popBackStack() }) }
        composable("actividades") {
            ActividadesScreen()
        }
    }
}
