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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.ConsignacionDialog
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.components.global.Background
import com.example.sisvitafrontend.components.global.CustomHeader
import com.example.sisvitafrontend.viewmodels.RealizarVigilanciaViewModel

@Composable
fun PatientData(
    navController: NavController,
    id: String,
    realizarVigilanciaViewModel: RealizarVigilanciaViewModel = viewModel(
        modelClass = RealizarVigilanciaViewModel::class
    )
) {
    val testResponse by realizarVigilanciaViewModel.testResponse.observeAsState()
    realizarVigilanciaViewModel.getResolvedTestById(id.toLong())

    Background()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomHeader(DpSize(100.dp, 100.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        colorResource(id = R.color.light_green_100),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.patient_data),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_600)
                    )
                    testResponse?.let {
                        ItemText(
                            label = it.documentType,
                            value = it.document, color = R.color.black_900
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        ItemText(
                            label = stringResource(id = R.string.name), value =
                            testResponse!!.name, color = R.color.black_900
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        ItemText(
                            label = stringResource(id = R.string.last_name), value =
                            testResponse!!.lastName, color = R.color.black_900
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        ItemText(
                            label = stringResource(id = R.string.second_last_name),
                            value = testResponse!!.secondLastName, color = R.color.black_900
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        colorResource(id = R.color.light_green_100),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.test_results),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_600)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    testResponse?.let {
                        ItemText(
                            label = stringResource(id = R.string.name),
                            value = it.nameTemplateTest, color = R.color
                                .black_900
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        ItemText(
                            label = stringResource(id = R.string.interpretation),
                            value = it.interpretation,
                            color = when (it.colorClassification) {
                                stringResource(id = R.string.green) -> R.color.green_900
                                stringResource(id = R.string.yellow) -> R.color.yellow_900
                                stringResource(id = R.string.red) -> R.color.red_900
                                else -> R.color.black_900
                            }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        ItemText(
                            label = stringResource(id = R.string.result), value =
                            it.result.toString(),
                            color = R.color.black_900
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        colorResource(id = R.color.light_green_100),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = stringResource(id = R.string.answers),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_600)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    testResponse?.let {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp)
                                .height(200.dp)
                        ) {
                            itemsIndexed(it.answers) { index, answer ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = "${index + 1}. ${answer.statement}",
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        ItemText(
                                            label = stringResource(id = R.string.answer), value
                                            = answer.alternative, color = R.color.black_900
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                testResponse?.let {
                    ConsignacionDialog(
                        idResolvedTestResponse = it.id,
                        idPatient = it.idPatient
                    )
                }
            }
        }
    }
}

@Composable
fun ItemText(label: String, value: String, color: Int) {
    Row {
        // bold text
        Text(
            text = "$label: ",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = colorResource(id = color)
        )
    }
}