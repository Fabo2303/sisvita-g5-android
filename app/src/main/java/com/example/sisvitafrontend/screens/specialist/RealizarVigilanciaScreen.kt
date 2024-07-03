package com.example.sisvitafrontend.screens.specialist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.DateFilter
import com.example.sisvitafrontend.components.SelectList
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.components.global.Background
import com.example.sisvitafrontend.components.global.CustomHeader
import com.example.sisvitafrontend.navigation.Screen
import com.example.sisvitafrontend.viewmodels.FiltrosViewModel
import com.example.sisvitafrontend.viewmodels.RealizarVigilanciaViewModel

@Composable
fun RealizarVigilanciaScreen(
    filtrosViewModel: FiltrosViewModel = viewModel(modelClass = FiltrosViewModel::class.java),
    realizarVigilanciaViewModel: RealizarVigilanciaViewModel = viewModel(modelClass =
    RealizarVigilanciaViewModel::class.java),
    navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }

    val listTipoTest by filtrosViewModel.listTipoTest.observeAsState(initial = emptyList())
    val listAnsiedad by filtrosViewModel.listAnsiedad.observeAsState(initial = emptyList())
    val listTestResponse by realizarVigilanciaViewModel.listTest.observeAsState(initial = emptyList())

    Background()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        CustomHeader(DpSize(100.dp, 100.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            TextButton(
                text = R.string.filters,
                color = R.color.light_green_300,
                textColor = R.color.black_900,
                shape = 12,
                size = DpSize(100.dp, 40.dp),
                textSize = 12,
                onClick = { expanded = !expanded }
            )
            Spacer(modifier = Modifier.width(10.dp))
            TextButton(
                text = R.string.heat_map,
                color = R.color.light_green_300,
                textColor = R.color.black_900,
                shape = 12,
                size = DpSize(200.dp, 40.dp),
                textSize = 12,
                onClick = {
                    navController.navigate(Screen.HeatMapScreen.route)
                }
            )
        }
        if (expanded){
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                SelectList(
                    label = R.string.test_type,
                    selectedItem = filtrosViewModel.tipoTest.value,
                    items = listTipoTest,
                    onItemSelected = {
                        filtrosViewModel.setTipoTest(it)
                        filtrosViewModel.setAnsiedad("")
                        filtrosViewModel.getAnsiedad(it)
                    }
                )
                DateFilter(
                    label = R.string.date,
                    startDateLabel = R.string.initial_date,
                    endDateLabel = R.string.final_date,
                    startDate = filtrosViewModel.fechaInicial.value,
                    endDate = filtrosViewModel.fechaFinal.value,
                    onDateRangeSelected = { startDate, endDate ->
                        filtrosViewModel.setFechaInicial(startDate)
                        filtrosViewModel.setFechaFinal(endDate)
                    },
                    onDateSelected = {
                        filtrosViewModel.setTextDate("${filtrosViewModel.fechaInicial.value} - ${filtrosViewModel.fechaFinal.value}")
                    }
                )
                SelectList(
                    label = R.string.anxiety_level,
                    selectedItem = filtrosViewModel.ansiedad.value,
                    items = listAnsiedad,
                    onItemSelected = { filtrosViewModel.setAnsiedad(it) }
                )
                TextButton(
                    text = R.string.apply_filters,
                    color = R.color.light_green_300,
                    textColor = R.color.black_900,
                    shape = 12,
                    size = DpSize(200.dp, 40.dp),
                    textSize = 12,
                    onClick = {
                        expanded = false
                        realizarVigilanciaViewModel.getResolvedTestByFilter(
                            filtrosViewModel.tipoTest.value,
                            filtrosViewModel.fechaInicial.value,
                            filtrosViewModel.fechaFinal.value,
                            filtrosViewModel.ansiedad.value,
                            filtrosViewModel.textDate.value
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    text = R.string.clear_filters,
                    color = R.color.light_green_300,
                    textColor = R.color.black_900,
                    shape = 12,
                    size = DpSize(200.dp, 40.dp),
                    textSize = 12,
                    onClick = {
                        expanded = false
                        filtrosViewModel.clearFilter()
                        realizarVigilanciaViewModel.getResolvedTestResponse()
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TableHeader()
            if (listTestResponse.isNotEmpty()) {
                listTestResponse.forEach {
                    TableRow(
                        it.id,
                        it.namePatient,
                        it.lastNamePatient,
                        it.nameTemplateTest,
                        it.date,
                        it.colorClassification,
                        navController
                    )
                }
            }
        }
    }
}

@Composable
fun TableHeader(){
    Row(
        modifier = Modifier
            .background(Color(0xFF1A936F))
    ){
        TableItem(stringResource(id = R.string.name), 80, 30)
        TableItem(stringResource(id = R.string.last_names), 80, 30)
        TableItem(stringResource(id = R.string.test), 100, 30)
        TableItem(stringResource(id = R.string.date), 90, 30)
        TableItem(stringResource(id = R.string.details), 50, 30)
    }
}

@Composable
fun TableRow(
    id: Long,
    nombre: String,
    apellidos: String,
    tipoTest: String,
    fecha: String,
    color: String,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .background(
                when (color) {
                    stringResource(id = R.string.green) -> colorResource(id = R.color.green_500)
                    stringResource(id = R.string.yellow) -> colorResource(id = R.color.yellow_500)
                    stringResource(id = R.string.red) -> colorResource(id = R.color.red_500)
                    else -> Color.White
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableItem(nombre, 80)
        TableItem(apellidos, 80)
        TableItem(tipoTest, 100)
        TableItem(fecha, 90)
        IconButton(onClick = {
            navController.navigate("patient_data/${id}")
        }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                Modifier.background(Color.White),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun TableItem(text: String, width: Int, height: Int = 70) {
    Box(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier
        )
    }
}