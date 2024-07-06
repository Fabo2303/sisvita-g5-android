package com.example.sisvitafrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sisvitafrontend.config.navigation.Navigation
import com.example.sisvitafrontend.ui.theme.SisvitaFrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SisvitaFrontendTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SisvitaApp()
                }
            }
        }
    }
}

@Composable
fun SisvitaApp(){
    Navigation()
}