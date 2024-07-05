package com.example.sisvitafrontend.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun DateFilter(
    label: Int,
    startDateLabel: Int,
    endDateLabel: Int,
    startDate: Date,
    endDate: Date,
    onDateRangeSelected: (Date, Date) -> Unit,
    onDateSelected: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("America/Lima")
    var showDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    if (showDialog){
        Dialog(onDismissRequest = { showDialog = false}) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Elegir rango de fechas")
                    DateRangePickerComponent(
                        startDateLabel = startDateLabel,
                        endDateLabel = endDateLabel,
                        startDate = startDate,
                        endDate = endDate,
                        onDateRangeSelected = onDateRangeSelected
                    )
                    Button(onClick = {
                        text = "${dateFormat.format(startDate)}, - ${dateFormat.format(endDate)}"
                        onDateSelected()
                        showDialog = false
                    }) {
                        Text(text = "Aceptar")
                    }
                    Button(onClick = {
                        text = ""
                        showDialog = false
                    }) {
                        Text(text = "Limpiar")
                    }
                }
            }
        }
    }

    TextField(
        value = text,
        onValueChange = { },
        readOnly = true,
        label = { Text(stringResource(id = label)) },
        trailingIcon = {
            IconButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Filled.DateRange, contentDescription = null)
            }
        }
    )
}