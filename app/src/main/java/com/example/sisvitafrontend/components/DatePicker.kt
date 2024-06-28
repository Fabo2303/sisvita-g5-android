package com.example.sisvitafrontend.components

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
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
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePickerComponent(
    label: Int,
    selectedDate: Date,
    onDateSelected: (Date) -> Unit
) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val context = LocalContext.current
    val calendar = Calendar.getInstance().apply { time = selectedDate }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val selectedSqlDate = Date(calendar.timeInMillis)
                onDateSelected(selectedSqlDate)
                showDialog = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.setOnDismissListener { showDialog = false }
        datePickerDialog.show()
    }

    TextField(
        value = dateFormat.format(selectedDate),
        onValueChange = { /* no-op */ },
        readOnly = true,
        label = { Text(stringResource(id = label)) },
        modifier = Modifier.clickable { showDialog = true },
        trailingIcon = {
            androidx.compose.material3.IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        }
    )
}
