package com.example.sisvitafrontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sisvitafrontend.R

@Composable
fun CustomDialog(
    buttonText: Int,
    title: String,
    content: String,
    color: Int = R.color.black_900,
    onClick: () -> Unit,
    dialogOnClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        colorResource(id = R.color.light_green_100),
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = color),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextButton(
                    text = R.string.accept,
                    color = R.color.light_green_500,
                    textColor = R.color.black_900,
                    shape = 12,
                    size = DpSize(100.dp, 35.dp),
                    textSize = 12
                ) {
                    showDialog = false
                    dialogOnClick()
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }

    TextButton(
        text = buttonText,
        color = R.color.light_green_300,
        textColor = R.color.black_900,
        shape = 12,
        size = DpSize(150.dp, 80.dp),
        textSize = 20,
        onClick = {
            showDialog = true
            onClick()
        }
    )
}