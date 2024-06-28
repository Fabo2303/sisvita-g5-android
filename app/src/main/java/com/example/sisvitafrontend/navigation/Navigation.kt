package com.example.sisvitafrontend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sisvitafrontend.screens.HomeScreen
import com.example.sisvitafrontend.screens.patient.MenuPatientScreen
import com.example.sisvitafrontend.screens.patient.MenuTestScreen
import com.example.sisvitafrontend.screens.patient.RealizarTestScreen
import com.example.sisvitafrontend.screens.specialist.HeatMapActivity
import com.example.sisvitafrontend.screens.specialist.MenuSpecialistScreen
import com.example.sisvitafrontend.screens.specialist.RealizarVigilanciaScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.MenuPatientScreen.route){
            MenuPatientScreen(navController = navController)
        }
        composable(route = Screen.MenuSpecialistScreen.route){
            MenuSpecialistScreen(navController = navController)
        }
        composable(route = Screen.MenuTestScreen.route){
            MenuTestScreen(navController = navController)
        }

        composable(route = Screen.RealizarVigilanciaScreen.route){
            RealizarVigilanciaScreen()
        }

        composable(
            route = Screen.RealizarTestScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ){
            backStackEntry -> val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                RealizarTestScreen(navController = navController, id = id)
            }
        }
    }
}

// Login para api
// Especialista
// juangomez
// clave123
// Paciente
// pepito
// clave123