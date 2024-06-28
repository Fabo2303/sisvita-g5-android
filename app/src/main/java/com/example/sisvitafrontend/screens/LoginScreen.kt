package com.example.sisvitafrontend.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.api.datastore.DataStoreManager
import com.example.sisvitafrontend.components.CustomEditTextField
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(modelClass = LoginViewModel::class.java),
    navController: NavController,
    showScreen: MutableState<String>
) {
    Spacer(modifier = Modifier.height(30.dp))
    Formulario(
        loginViewModel = loginViewModel,
        navController = navController
    )
    Spacer(modifier = Modifier.height(15.dp))
    Footer(showScreen)
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
private fun Footer(showScreen: MutableState<String>) {
    Row {
        Text(
            text = stringResource(id = R.string.no_tienes_una_cuenta),
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(id = R.string.registrarse),
            modifier = Modifier.clickable {
                showScreen.value = "register"
            },
            color = colorResource(id = R.color.text_red_900)

        )
    }
}

@Composable
private fun Formulario(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val user by loginViewModel.user.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val context = LocalContext.current
    val dataStoreManager = remember {
        DataStoreManager(context)
    }

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
    TextButton(
        text = R.string.ingresar,
        color = R.color.button_light,
        textColor = R.color.text_black_900,
        shape = 12,
        size = DpSize(150.dp, 80.dp),
        textSize = 20,
        onClick = {
            loginViewModel.onLoginClicked(dataStoreManager, navController)
        }
    )
}

