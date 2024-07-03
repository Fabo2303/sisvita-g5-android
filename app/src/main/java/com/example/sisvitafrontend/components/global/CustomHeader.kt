package com.example.sisvitafrontend.components.global

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.sisvitafrontend.R

@Composable
fun CustomHeader(iconSize: DpSize = DpSize(200.dp, 200.dp)) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white_900), shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 0,
                    bottomStartPercent = 0,
                    bottomEndPercent = 50
                )
            ), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rounded_logo),
            contentDescription = stringResource(id = R.string.logo_sisvita),
            modifier = Modifier.size(iconSize)
        )
    }
}