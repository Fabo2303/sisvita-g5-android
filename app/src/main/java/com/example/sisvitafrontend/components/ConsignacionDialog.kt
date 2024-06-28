package com.example.sisvitafrontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.viewmodels.ConsignacionViewModel

@Composable
fun ConsignacionDialog(
    idResolvedTestResponse: Long,
    listAnsiedad: List<String>,
    consignacionViewModel: ConsignacionViewModel = viewModel(modelClass = ConsignacionViewModel::class.java)
) {
    var showDialog by remember { mutableStateOf(false) }
    val interpretacion by consignacionViewModel.newInterpretation.observeAsState(initial = "")
    val observation by consignacionViewModel.observation.observeAsState(initial = "")
    val message by consignacionViewModel.message.observeAsState(initial = "")
    val medicalAppointmentRequested by consignacionViewModel.medicalAppointmentRequested.observeAsState(initial = false)

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false}) {
            Surface(
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    SelectList(label = R.string.nueva_interpretacion, selectedItem = interpretacion, items =
                    listAnsiedad, onItemSelected = consignacionViewModel::setNewInterpretation)

                    TextField(
                        value = observation,
                        onValueChange = { consignacionViewModel.setObservation(it) },
                        label = { Text(stringResource(id = R.string.observacion)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        ),
                        maxLines = 5,
                    )
                    
                    TextField(
                        value = message,
                        onValueChange = { consignacionViewModel.setMessage(it) },
                        label = { Text("Mensaje") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        )
                    )
                    Row {
                        Text("Solicitar cita médica")
                        Spacer(modifier = Modifier.width(8.dp))
                        Checkbox(
                            checked = medicalAppointmentRequested,
                            onCheckedChange =  { consignacionViewModel.setMedicalAppointmentRequested(it) },
                        )
                    }

                    Row {
                        Button(onClick = {
                            consignacionViewModel.saveConsignacion(idtest = idResolvedTestResponse)
                        }) {
                            Text("Guardar")
                        }

                        Button(onClick = { showDialog = false }) {
                            Text("Cerrar")
                        }
                    }
                }
            }
        }
    }

    Button(onClick = {
        showDialog = true
    }) {
        Text("Consignar")
    }
}