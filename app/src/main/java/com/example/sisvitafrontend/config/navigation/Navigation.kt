package com.example.sisvitafrontend.config.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sisvitafrontend.screens.HomeScreen
import com.example.sisvitafrontend.screens.MenuPatientScreen
import com.example.sisvitafrontend.screens.realizartest.MenuTestScreen
import com.example.sisvitafrontend.screens.realizartest.RealizarTestScreen
import com.example.sisvitafrontend.screens.realizarvigilancia.visualizarmapacalor.HeatmapScreen
import com.example.sisvitafrontend.screens.MenuSpecialistScreen
import com.example.sisvitafrontend.screens.consultarresultados.ConsultarResultados
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.PatientData
import com.example.sisvitafrontend.screens.realizarvigilancia.realizarvigilanciatable.RealizarVigilanciaScreen

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

        composable(route = Screen.ConsultarResultadosScreen.route){
            ConsultarResultados()
        }

        composable(route = Screen.MenuTestScreen.route){
            MenuTestScreen(navController = navController)
        }

        composable(route = Screen.RealizarVigilanciaScreen.route){
            RealizarVigilanciaScreen(navController = navController)
        }

        composable(route = Screen.HeatMapScreen.route){
            HeatmapScreen()
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

        composable(
            route = Screen.PatientData.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ){
            backStackEntry -> val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                PatientData(navController = navController, id = id)
            }
        }
    }
}