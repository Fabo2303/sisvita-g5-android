package com.example.sisvitafrontend.components

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern
import com.example.sisvitafrontend.R

@Composable
fun CustomEditTextField(
    label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    type: Int,
){
    when(type){
        0 -> CommonTextField(label, keyboardOptions, value, onValueChanged)
        1 -> PasswordTextField(label, keyboardOptions, value, onValueChanged)
    }
}

@Composable
fun CommonTextField(
    label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
){
    TextField(
        value = value,
        singleLine = true,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )

}

@Composable
fun CustomTextField(
    label: Int,
    placeholder: Int,
    keyboardType: KeyboardType,
    value: String,
    onValueChanged: (String) -> Unit,
    alert: Int,
    pattern: (String) -> Boolean
){
    var isValid by remember { mutableStateOf(true) }
    Column(
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
                isValid = pattern(it)
            },
            label = { Text(stringResource(id = label)) },
            placeholder = { Text(stringResource(id = placeholder)) },
            isError = !isValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType, imeAction = ImeAction.Next
            ),
        )
        if (!isValid) {
            Text(
                text = stringResource(id = alert),
                color = colorResource(id = R.color.red_900)
            )
        }
    }
}

@Composable
fun PasswordTextField(
    label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        singleLine = true,
        onValueChange = onValueChanged,
        label = { Text(stringResource(id = label)) },
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        }
    )
}