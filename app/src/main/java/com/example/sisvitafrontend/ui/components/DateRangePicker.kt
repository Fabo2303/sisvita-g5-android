package com.example.sisvitafrontend.ui.components

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Composable
fun DateRangePickerComponent(
    startDateLabel: Int,
    endDateLabel: Int,
    startDate: Date,
    endDate: Date,
    onDateRangeSelected: (Date, Date) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("America/Lima")
    val context = LocalContext.current
    val startCalendar = Calendar.getInstance().apply { time = startDate }
    val endCalendar = Calendar.getInstance().apply { time = endDate }

    var showStartDialog by remember { mutableStateOf(false) }
    var showEndDialog by remember { mutableStateOf(false) }

    if (showStartDialog) {
        val startDatePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                startCalendar.set(year, month, dayOfMonth)
                val selectedStartDate = Date(startCalendar.timeInMillis)
                if (selectedStartDate <= endDate) {
                    onDateRangeSelected(selectedStartDate, endDate)
                } else {
                    Toast.makeText(context, "La fecha de inicio debe ser menor o igual a la fecha de fin", Toast.LENGTH_SHORT).show()
                }
                showStartDialog = false
            },
            startCalendar.get(Calendar.YEAR),
            startCalendar.get(Calendar.MONTH),
            startCalendar.get(Calendar.DAY_OF_MONTH)
        )
        startDatePickerDialog.setOnDismissListener { showStartDialog = false }
        startDatePickerDialog.show()
    }

    if (showEndDialog) {
        val endDatePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                endCalendar.set(year, month, dayOfMonth)
                val selectedEndDate = Date(endCalendar.timeInMillis)
                if (selectedEndDate >= startDate) {
                    onDateRangeSelected(startDate, selectedEndDate)
                } else {
                    Toast.makeText(context, "La fecha de fin debe ser mayor o igual a la fecha de inicio", Toast.LENGTH_SHORT).show()
                }
                showEndDialog = false
            },
            endCalendar.get(Calendar.YEAR),
            endCalendar.get(Calendar.MONTH),
            endCalendar.get(Calendar.DAY_OF_MONTH)
        )
        endDatePickerDialog.setOnDismissListener { showEndDialog = false }
        endDatePickerDialog.show()
    }

    Column {
        TextField(
            value = dateFormat.format(startDate),
            onValueChange = { /* no-op */ },
            readOnly = true,
            label = { Text(stringResource(id = startDateLabel)) },
            modifier = Modifier.clickable { showStartDialog = true },
            trailingIcon = {
                IconButton(onClick = { showStartDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = dateFormat.format(endDate),
            onValueChange = { /* no-op */ },
            readOnly = true,
            label = { Text(stringResource(id = endDateLabel)) },
            modifier = Modifier.clickable { showEndDialog = true },
            trailingIcon = {
                IconButton(onClick = { showEndDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            }
        )
    }
}
