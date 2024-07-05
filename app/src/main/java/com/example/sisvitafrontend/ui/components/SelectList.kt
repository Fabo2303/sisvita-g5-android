package com.example.sisvitafrontend.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

@Composable
fun SelectList(
    label: Int,
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
){
    var showDialog by remember { mutableStateOf(false)}

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = label),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyColumn {
                        items(items){ item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    onItemSelected(item)
                                    showDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    TextField(
        value = selectedItem,
        onValueChange = { },
        readOnly = true,
        label = { Text(stringResource(id = label)) },
        modifier = Modifier.clickable { showDialog = true },
        trailingIcon = {
            androidx.compose.material3.IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
    )
}