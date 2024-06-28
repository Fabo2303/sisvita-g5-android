package com.example.sisvitafrontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sisvitafrontend.api.responses.PatientResponse
import com.example.sisvitafrontend.api.responses.ResolvedTestResponse

@Composable
fun PatientDataDialog(
    resolvedTestResponse: ResolvedTestResponse
) {
    val patientResponse: PatientResponse = resolvedTestResponse.paciente
    val userResponse = patientResponse.user
    val personaResponse = userResponse.persona

    var showDialog by remember { mutableStateOf(false) }

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
                    Text(
                        text = "Datos del paciente",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Nombre: ${personaResponse.name}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Apellido Paterno: ${personaResponse.lastName}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Apellido Materno: ${personaResponse.secondLastName}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Fecha de nacimiento: ${personaResponse.birthDate}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Sexo: ${personaResponse.sex}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Email: ${personaResponse.email}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Resultados del test"
                    )

                    Text(
                        text = "Nombre: ${resolvedTestResponse.templateTest.name}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Fecha: ${resolvedTestResponse.date}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Interpretación: ${resolvedTestResponse.classification.interpretation}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Resultado: ${resolvedTestResponse.result}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Button(onClick = { showDialog = false }) {
                        Text("Cerrar")
                    }

                    ConsignacionDialog(
                        idResolvedTestResponse = resolvedTestResponse.id,
                        listAnsiedad = resolvedTestResponse.templateTest.classifications.map { it.interpretation }
                    )
                }
            }
        }
    }

    IconButton(onClick = {
        showDialog = true
    }) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            Modifier.background(Color.White),
            tint = Color.Black
        )
    }
}