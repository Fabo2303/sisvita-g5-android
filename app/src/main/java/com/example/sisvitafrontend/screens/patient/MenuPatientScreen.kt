package com.example.sisvitafrontend.screens.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.Background
import com.example.sisvitafrontend.components.ImagenButton
import com.example.sisvitafrontend.navigation.Screen

@Composable
fun MenuPatientScreen(navController: NavController) {
    Background()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImagenButton(
                    text = R.string.realizar_test,
                    color = R.color.button_light,
                    textColor = R.color.text_black_900,
                    shape = 12,
                    size = DpSize(width = 300.dp, height = 80.dp),
                    image = R.drawable.test,
                    imageSize = 40,
                    textSize = 20,
                    onClick = {
                        navController.navigate(Screen.MenuTestScreen.route)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ImagenButton(
                    text = R.string.consultar_resultados,
                    color = R.color.button_light,
                    textColor = R.color.text_black_900,
                    shape = 12,
                    size = DpSize(width = 300.dp, height = 80.dp),
                    image = R.drawable.sociology,
                    imageSize = 40,
                    textSize = 20,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(16.dp))
                ImagenButton(
                    text = R.string.realizar_test,
                    color = R.color.button_light,
                    textColor = R.color.text_black_900,
                    shape = 12,
                    size = DpSize(width = 300.dp, height = 80.dp),
                    image = R.drawable.test,
                    imageSize = 40,
                    textSize = 20,
                    onClick = {}
                )
            }
        }
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