package com.example.sisvitafrontend.screens.consultarresultados

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.config.datastore.DataStoreManager
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.ItemText
import com.example.sisvitafrontend.ui.components.Background
import com.example.sisvitafrontend.ui.components.CustomHeader
import com.example.sisvitafrontend.ui.components.TextButton

@Composable
fun ConsultarResultados(
    consultarResultadosViewModel: ConsultarResultadosViewModel = viewModel(
        modelClass = ConsultarResultadosViewModel::class
    )
) {

    val context = LocalContext.current
    val dataStoreManager = remember {
        DataStoreManager(context)
    }
    val listTest = consultarResultadosViewModel.listTest.observeAsState(emptyList())
    consultarResultadosViewModel.getResolvedTest(dataStoreManager)
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
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
                    .height(500.dp)
            ) {
                items(listTest.value.size) { index ->
                    val test = listTest.value[index]
                    TestCard(test)
                }
            }
        }
    }
}

@Composable
fun TestCard(test: ResolvedTestResponseViewPatient) {
    var expanded = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(10.dp)
            .background(
                colorResource(id = R.color.light_green_300)
            )
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.test),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = test.nameTemplateTest)
        }
        Row {
            Text(
                text = stringResource(id = R.string.date),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = test.date)
        }
        Row {
            Text(
                text = stringResource(id = R.string.result),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "${test.result}")
        }
        Row {
            Text(
                text = stringResource(id = R.string.interpretation),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = test.interpretation,
                color = colorResource(
                    id = when (test.colorClassification) {
                        stringResource(id = R.string.green) -> R.color.green_900
                        stringResource(id = R.string.yellow) -> R.color.yellow_900
                        stringResource(id = R.string.red) -> R.color.red_900
                        else -> R.color.black_900
                    }
                )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                text = R.string.see_more,
                color = R.color.light_green_100,
                textColor = R.color.black_900,
                shape = 12,
                size = DpSize(120.dp, 40.dp),
                textSize = 16
            ) {
                expanded.value = !expanded.value
            }
        }
        if (expanded.value) {
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.answers),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp)
                    .height(100.dp)
                    .background(
                        colorResource(id = R.color.light_green_100)
                    )
            ) {
                itemsIndexed(test.answers) { index, answer ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "${index + 1}. ${answer.statement}"
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
            if (test.consignation != null) {
                Column(
                    modifier = Modifier.fillMaxWidth(1f),
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.consignation),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.date),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.date)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.observation),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.observation)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.fundament),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.fundament)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.specialist_name),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.nameSpecialist)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.diagnosis),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.nameDiagnosis)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.fundament),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.fundamentDiagnosis)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.treatment),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.nameTreatment)
                    }
                    Row {
                        Text(
                            text = stringResource(id = R.string.fundament),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = test.consignation.fundamentTreatment)
                    }
                }
            }
        }
    }
}