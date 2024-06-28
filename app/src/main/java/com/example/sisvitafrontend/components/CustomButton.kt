package com.example.sisvitafrontend.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(
    text: Int,
    color: Int,
    textColor: Int,
    shape: Int,
    size: DpSize,
    textSize: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(shape.dp),
        modifier = Modifier.size(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(
                id = color
            )
        )
    ) {
        Text(
            text = stringResource(id = text),
            color = colorResource(id = textColor),
            fontSize = textSize.sp
        )
    }
}

@Composable
fun TextButton(
    text: String,
    color: Int,
    textColor: Int,
    shape: Int,
    size: DpSize,
    textSize: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(shape.dp),
        modifier = Modifier.size(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(
                id = color
            )
        )
    ) {
        Text(
            text = text,
            color = colorResource(id = textColor),
            fontSize = textSize.sp
        )
    }
}

@Composable
fun ImagenButton(
    text: Int,
    color: Int,
    textColor: Int,
    shape: Int,
    size: DpSize,
    image: Int,
    textSize: Int,
    imageSize: Int,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(shape.dp),
        modifier = Modifier.size(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(
                id = color
            )
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = text),
                color = colorResource(id = textColor),
                fontSize = textSize.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(imageSize.dp)
            )
        }
    }
}

