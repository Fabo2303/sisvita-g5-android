package com.example.sisvitafrontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ResultDialog(
    result: Int,
    interpretation: String,
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(result == 0 && interpretation.isEmpty()){
                        Text(text = "Cargando ...")
                    }else{
                        Text(text = "Test realizado con éxito")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Resultado: $result")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Interpretación: $interpretation")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { showDialog = false }) {
                            Text(text = "Aceptar")
                        }
                    }
                }
            }
        }
    }

    Button(onClick = {
        showDialog = true
        onClick()
    }) {
        Text(text = "Enviar Test")
    }
}