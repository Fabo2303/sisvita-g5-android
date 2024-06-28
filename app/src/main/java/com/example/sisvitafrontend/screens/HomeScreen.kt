package com.example.sisvitafrontend.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.Background
import com.example.sisvitafrontend.components.TextButton

@Composable
fun HomeScreen(
    navController: NavController
) {
    val showScreen = remember { mutableStateOf("login") }

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
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .background(
                    colorResource(id = R.color.container),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RowButtons(
                    showScreen = {
                        showScreen.value = it
                    },
                    showScreen.value
                )
                if (showScreen.value == "login")
                    LoginScreen(
                        navController = navController,
                        showScreen = showScreen
                    )
                else
                    RegisterScreen(
                        showScreen = showScreen
                    )
            }
        }
    }
}

@Composable
private fun RowButtons(showScreen: (String) -> Unit, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .background(
                colorResource(id = R.color.button_light),
                shape = RoundedCornerShape(12.dp)
            )
    )
    {
        TextButton(
            text = R.string.iniciar_sesion,
            textColor = R.color.text_black_900,
            shape = 12,
            color = if (value == "login") R.color.button_dark else R.color.button_light,
            size = DpSize(150.dp, 80.dp),
            textSize = 20,
            onClick = {
                showScreen("login")
            }
        )
        Spacer(modifier = Modifier.width(1.dp))
        TextButton(
            text = R.string.registrarse,
            textColor = R.color.text_black_900,
            shape = 12,
            color = if (value == "register") R.color.button_dark else R.color.button_light,
            size = DpSize(150.dp, 80.dp),
            textSize = 20,
            onClick = {
                showScreen("register")
            }
        )
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.header), shape = RoundedCornerShape(
                    topStartPercent = 0,
                    topEndPercent = 0,
                    bottomStartPercent = 0,
                    bottomEndPercent = 50
                )
            ), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rounded_logo),
            contentDescription = "logo sisvita",
            modifier = Modifier.size(200.dp)
        )
    }
}