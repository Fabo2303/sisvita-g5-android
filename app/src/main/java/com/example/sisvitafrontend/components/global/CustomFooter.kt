package com.example.sisvitafrontend.components.global

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sisvitafrontend.R

@Composable
fun CustomFooter(
    showScreen: MutableIntState? = null,
    value: Int? = null,
    blackText: Int,
    redText: Int
) {
    Row {
        Text(
            text = stringResource(id = blackText),
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(id = redText),
            modifier = Modifier.clickable {
                if (showScreen != null) {
                    showScreen.intValue = value!!
                }
            },
            color = colorResource(id = R.color.red_900)
        )
    }
}