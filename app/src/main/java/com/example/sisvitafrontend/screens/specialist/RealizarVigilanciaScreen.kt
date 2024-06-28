package com.example.sisvitafrontend.screens.specialist

import android.content.Intent
import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse
import com.example.sisvitafrontend.components.Background
import com.example.sisvitafrontend.components.DateFilter
import com.example.sisvitafrontend.components.DatePickerComponent
import com.example.sisvitafrontend.components.DateRangePickerComponent
import com.example.sisvitafrontend.components.PatientDataDialog
import com.example.sisvitafrontend.components.SelectList
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.viewmodels.FiltrosViewModel
import com.example.sisvitafrontend.viewmodels.RealizarVigilanciaViewModel

@Composable
fun RealizarVigilanciaScreen(
    filtrosViewModel: FiltrosViewModel = viewModel(modelClass = FiltrosViewModel::class.java),
    realizarVigilanciaViewModel: RealizarVigilanciaViewModel = viewModel(modelClass = RealizarVigilanciaViewModel::class.java)
) {
    var expanded by remember { mutableStateOf(false) }

    val listTipoTest by filtrosViewModel.listTipoTest.observeAsState(initial = emptyList())
    val listAnsiedad by filtrosViewModel.listAnsiedad.observeAsState(initial = emptyList())
    val listTest by realizarVigilanciaViewModel.listTest.observeAsState(initial = emptyList())
    val context = LocalContext.current
    //realizarVigilanciaViewModel.getResolvedTest()

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
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            TextButton(
                text = R.string.filtros,
                color = R.color.button_light,
                textColor = R.color.text_black_900,
                shape = 12,
                size = DpSize(100.dp, 40.dp),
                textSize = 12,
                onClick = { expanded = !expanded }
            )
            Spacer(modifier = Modifier.width(10.dp))
            TextButton(
                text = R.string.mapa_de_calor,
                color = R.color.button_light,
                textColor = R.color.text_black_900,
                shape = 12,
                size = DpSize(200.dp, 40.dp),
                textSize = 12,
                onClick = {
                    val intent = Intent(context, HeatMapActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
        if (expanded){
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                SelectList(
                    label = R.string.tipo_de_test,
                    selectedItem = filtrosViewModel.tipoTest.value,
                    items = listTipoTest,
                    onItemSelected = {
                        filtrosViewModel.setTipoTest(it)
                        filtrosViewModel.setAnsiedad("")
                        filtrosViewModel.getAnsiedad(it)
                    }
                )
                DateFilter(
                    label = R.string.fecha,
                    startDateLabel = R.string.fecha_inicial,
                    endDateLabel = R.string.fecha_final,
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
                    label = R.string.nivel_de_ansiedad,
                    selectedItem = filtrosViewModel.ansiedad.value,
                    items = listAnsiedad,
                    onItemSelected = { filtrosViewModel.setAnsiedad(it) }
                )
                TextButton(
                    text = R.string.aplicar_filtros,
                    color = R.color.button_light,
                    textColor = R.color.text_black_900,
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
                    text = R.string.limpiar_filtros,
                    color = R.color.button_light,
                    textColor = R.color.text_black_900,
                    shape = 12,
                    size = DpSize(200.dp, 40.dp),
                    textSize = 12,
                    onClick = {
                        expanded = false
                        filtrosViewModel.clearFilter()
                        realizarVigilanciaViewModel.getResolvedTest()
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TableHeader()
            if (listTest.isNotEmpty()) {
                listTest.forEach {
                    TableRow(
                        it.paciente.user.persona.name,
                        it.paciente.user.persona.lastName,
                        it.templateTest.name,
                        it.date,
                        it.classification.interpretation,
                        it
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
        TableItem("Nombre", 80, 30)
        TableItem("Apellidos", 80, 30)
        TableItem("Test", 100, 30)
        TableItem("Fecha", 90, 30)
        TableItem("Ver", 50, 30)
    }
}

@Composable
fun TableRow(
    nombre: String,
    apellidos: String,
    tipoTest: String,
    fecha: String,
    ansiedad: String,
    resolvedTest: ResolvedTestResponse
) {
    Row(
        modifier = Modifier
            .background(
                when (ansiedad) {
                    "Ansiedad alta" -> Color(0xFFF25555)
                    "Ansiedad media" -> Color(0xFFF9DD6F)
                    "Ansiedad baja" -> Color(0xFF81F975)
                    "Ansiedad mínima" -> Color(0xFF81F975)
                    "Ansiedad leve" -> Color(0xFFF9DD6F)
                    "Ansiedad moderada" -> Color(0xFFFF5722)
                    "Ansiedad grave" -> Color(0xFFF25555)
                    "Ansiedad severa" -> Color(0xFFF25555)
                    else -> Color.White
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableItem(nombre, 80)
        TableItem(apellidos, 80)
        TableItem(tipoTest, 100)
        TableItem(fecha, 90)
        PatientDataDialog(resolvedTest)
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

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.header), shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 0,
                    bottomStartPercent = 0,
                    bottomEndPercent = 50
                )
            ), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rounded_logo),
            contentDescription = "logo sisvita",
            modifier = Modifier.size(100.dp)
        )
    }
}