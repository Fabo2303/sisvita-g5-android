package com.example.sisvitafrontend.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.components.CustomEditTextField
import com.example.sisvitafrontend.components.global.CustomDialog
import com.example.sisvitafrontend.components.global.CustomFooter
import com.example.sisvitafrontend.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(modelClass = LoginViewModel::class.java),
    navController: NavController,
    showScreen: MutableIntState
) {
    Spacer(modifier = Modifier.height(30.dp))
    Formulary(
        loginViewModel = loginViewModel,
        navController = navController
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomFooter(
        showScreen,
        R.string.register,
        R.string.no_account,
        R.string.register
    )
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
private fun Formulary(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val user by loginViewModel.user.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val title by loginViewModel.title.observeAsState("")
    val message by loginViewModel.message.observeAsState("")
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    CustomEditTextField(
        label = R.string.username,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = user,
        onValueChanged = { loginViewModel.onUserChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    CustomEditTextField(
        label = R.string.password,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = password,
        onValueChanged = { loginViewModel.onPasswordChanged(it) },
        type = 1
    )
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    CustomDialog(
        buttonText = R.string.login_button,
        title = title,
        content = message,
        onClick = { loginViewModel.onLoginClicked(dataStoreManager) },
        dialogOnClick = {
            loginViewModel.goToMenu(navController)
        }
    )
}

