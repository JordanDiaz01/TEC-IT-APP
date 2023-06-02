package com.diseno.login
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearCuenta(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var posta by remember { mutableStateOf(false) }
    var nuevo by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }
    LaunchedEffect(posta) {
        if (posta) {
            postData(nuevo)
            posta = false
        }
    }

    fun setOption(option: String, selectedOption: MutableState<String>) {
        selectedOption.value = option
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("") },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.widthIn(min = 80.dp)
                ) {
                    Text(if (switchState.value) "Volver" else "Back")
                }
            })
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(if (switchState.value) "Nombre" else "Name") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text(if (switchState.value) "Apellidos" else "Last Names") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(if (switchState.value) "Correo Electrónico" else "Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(if (switchState.value) "Contraseña" else "Password") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(if (switchState.value) "Confirmación de Contraseña" else "Confirm Password") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )
            Button(onClick = {
                if (name.isNullOrBlank() or surname.isNullOrBlank() or password.isNullOrBlank() or email.isNullOrBlank()){
                    showDialog.value = true
                    dialogMessage.value = "Por favor, completa todos los campos."
                }else{
                nuevo = "{\"nombrecliente\": \"${name}\",\"apellidocliente\": \"${surname}\",\"password\": \"${password}\",\"email\": \"${email}\"}"
                posta = true
                    navController.navigate("login")
                }

            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(if (switchState.value) "Confirmar" else "Confirm")
            }
            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text(text = "Error") },
                    text = { Text(text = dialogMessage.value) },
                    confirmButton = {
                        TextButton(onClick = { showDialog.value = false }) {
                            Text(text = "OK")
                        }
                    }
                )
            }
        }

    }
}
