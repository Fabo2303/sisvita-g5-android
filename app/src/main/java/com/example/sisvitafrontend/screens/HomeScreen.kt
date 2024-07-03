package com.example.sisvitafrontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.global.Background
import com.example.sisvitafrontend.components.global.CustomHeader
import com.example.sisvitafrontend.components.TextButton

@Composable
fun HomeScreen(
    navController: NavController
) {
    val showScreen = remember { mutableIntStateOf(R.string.login) }

    Background()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomHeader()
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .background(
                    colorResource(id = R.color.light_green_100), shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RowButtons(
                    showScreen = {
                        showScreen.intValue = it
                    }, showScreen.intValue
                )
                if (showScreen.intValue == R.string.login) LoginScreen(
                    navController = navController, showScreen = showScreen
                )
                else RegisterScreen(
                    showScreen = showScreen
                )
            }
        }
    }
}

@Composable
private fun RowButtons(showScreen: (Int) -> Unit, value: Int) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .background(
                colorResource(id = R.color.light_green_300), shape = RoundedCornerShape(12.dp)
            )
    ) {
        TextButton(text = R.string.login,
            textColor = R.color.black_900,
            shape = 12,
            color = if (value == R.string.login) R.color.light_green_500 else R.color.light_green_300,
            size = DpSize(150.dp, 80.dp),
            textSize = 17,
            onClick = {
                showScreen(R.string.login)
            })
        Spacer(modifier = Modifier.width(1.dp))
        TextButton(text = R.string.register,
            textColor = R.color.black_900,
            shape = 12,
            color = if (value == R.string.login) R.color.light_green_300 else R.color.light_green_500,
            size = DpSize(150.dp, 80.dp),
            textSize = 17,
            onClick = {
                showScreen(R.string.register)
            })
    }
}