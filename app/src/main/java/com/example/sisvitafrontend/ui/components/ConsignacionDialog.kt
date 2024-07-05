package com.example.sisvitafrontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.config.datastore.DataStoreManager
import com.example.sisvitafrontend.screens.realizarvigilancia.evaluarresultadostest.ConsignacionViewModel

@Composable
fun ConsignacionDialog(
    idResolvedTestResponse: Long,
    idPatient: Long,
    consignacionViewModel: ConsignacionViewModel = viewModel(modelClass = ConsignacionViewModel::class.java)
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(colorResource(id = R.color.light_green_100)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    EvaluacionForm(
                        consignacionViewModel,
                        idResolvedTestResponse,
                        idPatient
                    )
                }
            }
        }
    }

    TextButton(
        text = R.string.consign,
        color = R.color.light_green_300,
        textColor = R.color.black_900,
        shape = 12,
        size = DpSize(100.dp, 35.dp),
        textSize = 12,
        onClick = { showDialog = true }
    )
}

@Composable
fun EvaluacionForm(
    consignacionViewModel: ConsignacionViewModel,
    idResolvedTestResponse: Long,
    idPatient: Long
) {
    val context = LocalContext.current
    val dataStoreManager = remember {
        DataStoreManager(context)
    }
    val numberForm = remember { mutableIntStateOf(1) }
    Text(
        text = stringResource(id = R.string.evaluation),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(16.dp))
    when (numberForm.intValue) {
        1 -> FormDiagnostico(consignacionViewModel)
        2 -> FormTreatment(consignacionViewModel)
        3 -> FormObservacion(consignacionViewModel)
    }
    ChooseForm(increment = {
        if (numberForm.intValue < 3) numberForm.intValue += 1
    }, decrement = {
        if (numberForm.intValue > 1) numberForm.intValue -= 1
    })
    Spacer(modifier = Modifier.height(16.dp))
    TextButton(
        text = R.string.consign,
        color = R.color.light_green_500,
        textColor = R.color.black_900,
        shape = 12,
        size = DpSize(100.dp, 35.dp),
        textSize = 12,
        onClick = {
            consignacionViewModel.saveConsignacion(idResolvedTestResponse, idPatient, dataStoreManager)
        }
    )
}

@Composable
private fun ChooseForm(
    increment: () -> Unit,
    decrement: () -> Unit,
) {
    Row {
        IconButton(onClick = decrement) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Prev")
        }
        IconButton(onClick = increment) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}

@Composable
fun FormDiagnostico(
    consignacionViewModel: ConsignacionViewModel
) {
    var change by remember { mutableStateOf(false) }
    val diagnosticId by consignacionViewModel.diagnosticId.observeAsState(0L)
    val diagnostic by consignacionViewModel.diagnostic.observeAsState("")
    val diagnosticFundament by consignacionViewModel.diagnosticFundament.observeAsState("")
    val diagnosticList by consignacionViewModel.diagnosticList.observeAsState(emptyList())

    Text(
        text = stringResource(id = R.string.diagnostic)
    )
    Spacer(modifier = Modifier.height(8.dp))
    if (change) {
        CommonTextField(
            label = R.string.diagnostic,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            value = diagnostic,
            onValueChanged = { consignacionViewModel.setDiagnostic(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = diagnosticFundament,
            onValueChange = { consignacionViewModel.setDiagnosticFundament(it) },
            label = { Text(stringResource(id = R.string.fundament)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            maxLines = 5,
        )
        IconButton(onClick = {
            change = false
            consignacionViewModel.setDiagnosticId(0)
            consignacionViewModel.setDiagnostic("")
            consignacionViewModel.setDiagnosticFundament("")
        }) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = null,
                Modifier
                    .rotate(45f),
                tint = Color.Black,
            )
        }
    } else {
        SelectList(
            label = R.string.choose_diagnosis,
            selectedItem = diagnostic,
            items = diagnosticList.map { it.name },
            onItemSelected = {
                val diagnostico = diagnosticList.find { diagnostic -> diagnostic.name == it }
                consignacionViewModel.setDiagnosticId(diagnostico?.id ?: 0)
                consignacionViewModel.setDiagnostic(it)
                consignacionViewModel.setDiagnosticFundament(diagnostico?.fundament ?: "")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = diagnosticFundament,
            onValueChange = { consignacionViewModel.setDiagnosticFundament(it) },
            label = { Text(stringResource(id = R.string.fundament)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            maxLines = 5,
            readOnly = true
        )
        IconButton(onClick = {
            change = true
            consignacionViewModel.setDiagnosticId(0)
            consignacionViewModel.setDiagnostic("")
            consignacionViewModel.setDiagnosticFundament("")
        }) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = null,
                tint = Color.Black,
            )
        }
    }
}

@Composable
fun FormTreatment(
    consignacionViewModel: ConsignacionViewModel
) {
    var change by remember { mutableStateOf(false) }
    val treatmentId by consignacionViewModel.treatmentId.observeAsState(0L)
    val treatment by consignacionViewModel.treatment.observeAsState("")
    val treatmentFundament by consignacionViewModel.treatmentFundament.observeAsState("")
    val treatmentList by consignacionViewModel.treatmentList.observeAsState(emptyList())

    Text(
        text = stringResource(id = R.string.treatment)
    )
    Spacer(modifier = Modifier.height(8.dp))
    if (change) {
        CommonTextField(
            label = R.string.treatment,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            value = treatment,
            onValueChanged = { consignacionViewModel.setTreatment(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = treatmentFundament,
            onValueChange = { consignacionViewModel.setTreatmentFundament(it) },
            label = { Text(stringResource(id = R.string.fundament)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            maxLines = 5,
        )
        IconButton(onClick = {
            change = false
            consignacionViewModel.setTreatmentId(0)
            consignacionViewModel.setTreatment("")
            consignacionViewModel.setTreatmentFundament("")
        }) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = null,
                Modifier
                    .rotate(45f),
                tint = Color.Black,
            )
        }
    } else {
        SelectList(
            label = R.string.choose_treatment,
            selectedItem = treatment,
            items = treatmentList.map { it.name },
            onItemSelected = {
                val diagnostico = treatmentList.find { diagnostic -> diagnostic.name == it }
                consignacionViewModel.setTreatmentId(diagnostico?.id ?: 0)
                consignacionViewModel.setTreatment(it)
                consignacionViewModel.setTreatmentFundament(diagnostico?.fundament ?: "")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = treatmentFundament,
            onValueChange = { consignacionViewModel.setTreatmentFundament(it) },
            label = { Text(stringResource(id = R.string.fundament)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            maxLines = 5,
            readOnly = true
        )
        IconButton(onClick = {
            change = true
            consignacionViewModel.setTreatmentId(0)
            consignacionViewModel.setTreatment("")
            consignacionViewModel.setTreatmentFundament("")
        }) {
            Icon(
                imageVector = Icons.Filled.AddCircleOutline,
                contentDescription = null,
                tint = Color.Black,
            )
        }
    }
}

@Composable
fun FormObservacion(
    consignacionViewModel: ConsignacionViewModel
) {
    val observation by consignacionViewModel.observation.observeAsState("")
    val fundament by consignacionViewModel.fundament.observeAsState("")
    Text(
        text = stringResource(id = R.string.observation)
    )
    Spacer(modifier = Modifier.height(8.dp))
    CommonTextField(
        label = R.string.observation,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        value = observation,
        onValueChanged = { consignacionViewModel.setObservation(it) }
    )
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        value = fundament,
        onValueChange = { consignacionViewModel.setFundament(it) },
        label = { Text(stringResource(id = R.string.fundament)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Default
        ),
        maxLines = 5
    )
}