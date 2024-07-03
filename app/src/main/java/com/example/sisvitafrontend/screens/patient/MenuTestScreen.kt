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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.global.Background
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.components.global.CustomHeader
import com.example.sisvitafrontend.viewmodels.TemplateTestViewModel

@Composable
fun MenuTestScreen(
    navController: NavController,
    templateTestViewModel: TemplateTestViewModel = viewModel(modelClass = TemplateTestViewModel::class)
) {
    val templateTests by templateTestViewModel.templateTests.observeAsState(emptyList())
    templateTestViewModel.getTemplateTest()

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
        CustomHeader(DpSize(100.dp, 100.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(1f).height(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                items(templateTests) { templateTest ->
                    TextButton(
                        text = templateTest.name,
                        color = R.color.light_green_300,
                        textColor = R.color.black_900,
                        shape = 12,
                        size = DpSize(width = 300.dp, height = 80.dp),
                        textSize = 20,
                        onClick = {
                            navController.navigate("realizar_test_screen/${templateTest.id}")
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}