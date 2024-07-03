package com.example.sisvitafrontend.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object MenuPatientScreen: Screen("menu_patient_screen")
    object MenuSpecialistScreen: Screen("menu_specialist_screen")
    object MenuTestScreen: Screen("menu_test_screen")
    object RealizarTestScreen: Screen("realizar_test_screen/{id}")
    object PatientData: Screen("patient_data/{id}")
    object ConsultarResultadosScreen: Screen("consultar_resultados_screen")
    object RealizarVigilanciaScreen: Screen("realizar_vigilancia_screen")

    object HeatMapScreen: Screen("heat_map_screen")
}