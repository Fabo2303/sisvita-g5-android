package com.example.sisvitafrontend.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sisvitafrontend.R
import com.example.sisvitafrontend.components.CustomEditTextField
import com.example.sisvitafrontend.components.DatePickerComponent
import com.example.sisvitafrontend.components.SelectList
import com.example.sisvitafrontend.components.TextButton
import com.example.sisvitafrontend.viewmodels.RegisterViewModel
import com.example.sisvitafrontend.viewmodels.UbigeoViewModel
import java.sql.Date

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(modelClass = RegisterViewModel::class.java),
    ubigeoViewModel: UbigeoViewModel = viewModel(modelClass = UbigeoViewModel::class),
    showScreen: MutableState<String>
) {
    Formulario(
        registerViewModel = registerViewModel,
        ubigeoViewModel = ubigeoViewModel,
    )
    Spacer(modifier = Modifier.height(15.dp))
    Footer(
        showScreen = showScreen
    )
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
private fun Footer(
    showScreen: MutableState<String>
) {
    Row {
        Text(
            text = stringResource(id = R.string.ya_tienes_una_cuenta),
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(id = R.string.iniciar_sesion),
            modifier = Modifier.clickable {
                showScreen.value = "login"
            },
            color = colorResource(id = R.color.text_red_900)
        )
    }
}

@Composable
private fun Formulario1(
    registerViewModel: RegisterViewModel,
){
    val user by registerViewModel.user.observeAsState("")
    val password by registerViewModel.password.observeAsState("")
    CustomEditTextField(
        label = R.string.username,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = user,
        onValueChanged = { registerViewModel.onUserChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    CustomEditTextField(
        label = R.string.password,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = password,
        onValueChanged = { registerViewModel.onPasswordChanged(it) },
        type = 1
    )

}

@Composable
private fun Formulario2(
    registerViewModel: RegisterViewModel,
){
    val name by registerViewModel.name.observeAsState("")
    val lastName by registerViewModel.lastName.observeAsState("")
    val secondLastName by registerViewModel.secondLastName.observeAsState("")
    CustomEditTextField(
        label = R.string.nombre,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = name,
        onValueChanged = { registerViewModel.onNameChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.apellido_paterno,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = lastName,
        onValueChanged = { registerViewModel.onLastNameChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.apellido_materno,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = secondLastName,
        onValueChanged = { registerViewModel.onSecondLastNameChanged(it) },
        type = 0
    )

}

@Composable
private fun Formulario3(
    registerViewModel: RegisterViewModel,
){
    val document by registerViewModel.document.observeAsState("")
    val documentType by registerViewModel.documentType.observeAsState("")
    val birthdate by registerViewModel.birthdate.observeAsState(Date(System.currentTimeMillis()))

    CustomEditTextField(
        label = R.string.documento,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = document,
        onValueChanged = { registerViewModel.onDocumentChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    SelectList(
        label = R.string.tipo_documento,
        selectedItem = documentType,
        items = listOf("DNI", "Carnet de Extranjería", "Pasaporte", "Carnet Universitario"),
        onItemSelected = { registerViewModel.onDocumentTypeChanged(it) }
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    DatePickerComponent(
        label = R.string.fecha_nacimiento,
        selectedDate = birthdate,
        onDateSelected = { registerViewModel.onBirthdateChanged(it) }
    )
}

@Composable
private fun Formulario4(
    registerViewModel: RegisterViewModel,
){
    val sex by registerViewModel.sex.observeAsState("")
    val phone by registerViewModel.phone.observeAsState("")
    val email by registerViewModel.email.observeAsState("")

    SelectList(
        label = R.string.sexo,
        selectedItem = sex,
        items = listOf("Masculino", "Femenino", "Otro"),
        onItemSelected = { registerViewModel.onSexChanged(it) }
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.telefono,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = phone,
        onValueChanged = { registerViewModel.onPhoneChanged(it) },
        type = 0
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    CustomEditTextField(
        label = R.string.correo,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        value = email,
        onValueChanged = { registerViewModel.onEmailChanged(it) },
        type = 0
    )
}

@Composable
private fun Formulario5(
    registerViewModel: RegisterViewModel,
    ubigeoViewModel: UbigeoViewModel
){
    val departamento by registerViewModel.departamento.observeAsState("")
    val provincia by registerViewModel.provincia.observeAsState("")
    val distrito by registerViewModel.distrito.observeAsState("")

    val departamentos by ubigeoViewModel.departamentos.observeAsState(emptyList())
    val provincias by ubigeoViewModel.provincias.observeAsState(emptyList())
    val distritos by ubigeoViewModel.distritos.observeAsState(emptyList())

    SelectList(
        label = R.string.departamento,
        selectedItem = departamento,
        items = departamentos,
        onItemSelected = {
            registerViewModel.onDepartamentoChanged(it)
            registerViewModel.onProvinciaChanged("")
            registerViewModel.onDistritoChanged("")
            ubigeoViewModel.getProvincias(it)
        }
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    SelectList(
        label = R.string.provincia,
        selectedItem = provincia,
        items = provincias,
        onItemSelected = {
            registerViewModel.onProvinciaChanged(it)
            registerViewModel.onDistritoChanged("")
            ubigeoViewModel.getDistritos(departamento, it)
        }
    )
    Spacer(modifier = Modifier.padding(bottom = 10.dp))
    SelectList(
        label = R.string.distrito,
        selectedItem = distrito,
        items = distritos,
        onItemSelected = { registerViewModel.onDistritoChanged(it) }
    )
}

@Composable
private fun Formulario(
    registerViewModel: RegisterViewModel,
    ubigeoViewModel: UbigeoViewModel
) {
    val numberForm = remember { mutableIntStateOf(1) }

    when(numberForm.intValue){
        1 -> Formulario1(registerViewModel)
        2 -> Formulario2(registerViewModel)
        3 -> Formulario3(registerViewModel)
        4 -> Formulario4(registerViewModel)
        5 -> Formulario5(registerViewModel, ubigeoViewModel)
    }
    ChooseForm(
        increment = {
            if(numberForm.intValue < 5){
                numberForm.intValue += 1
            }
        },
        decrement = {
            if(numberForm.intValue > 1){
                numberForm.intValue -= 1
            }
        }
    )
    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    TextButton(
        text = R.string.registrarse,
        color = R.color.button_light,
        textColor = R.color.text_black_900,
        shape = 12,
        size = DpSize(150.dp, 50.dp),
        textSize = 20,
        onClick = {
            registerViewModel.onRegisterClicked()
        }
    )
}

@Composable
private fun ChooseForm(
    increment: () -> Unit,
    decrement: () -> Unit,
){
    Row {
        IconButton(onClick = decrement) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Prev")
        }
        IconButton(onClick = increment) {
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
        }
    }
}