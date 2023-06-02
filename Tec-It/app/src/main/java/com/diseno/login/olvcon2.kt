package com.diseno.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun olvcon2(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    var password by remember { mutableStateOf("") }
    val showConfirmation = remember { mutableStateOf(false) }

// Define si se muestra la contraseña en texto plano o enmascarada
    var passwordVisibility by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("") },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.widthIn(min = 80.dp)) {
                    Text(if (switchState.value)"Volver" else "Back")
                }
            })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Nueva Contraseña xd",
                modifier = Modifier.padding(top = 32.dp),
                //style = MaterialTheme.typography.h4
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(if (switchState.value) "Nueva Contraseña" else "New Password")},
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            OutlinedTextField(//PÓR MIENTRAS LUEGO LE QUITAREMOS QUE NO SEA DE MISMO VALOR
                value = password,
                onValueChange = { password = it },
                label = { Text(if (switchState.value) "Confirmacion De Contraseña" else "Password Confirmation") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            Button(onClick = {
                             showConfirmation.value = true
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(if (switchState.value) "Nueva Contraseña" else "New Password")
            }
            if (showConfirmation.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Restablecer el estado de la confirmación
                        showConfirmation.value = false
                    },
                    title = {
                        Text(if (switchState.value) "Confirmar nueva contraseña" else "Confirm new password")
                    },
                    text = {
                        Text(if (switchState.value) "¿Estás seguro de que deseas cambiar la contraseña?" else "Are you sure you want to change the password?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Realizar el cambio de contraseña
                                // ...
                                showConfirmation.value = false // Cerrar la ventana emergente de confirmación
                            }
                        ) {
                            Text(if (switchState.value) "Aceptar" else "Accept")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                // Cancelar el cambio de contraseña
                                showConfirmation.value = false // Cerrar la ventana emergente de confirmación
                            }
                        ) {
                            Text(if (switchState.value) "Cancelar" else "Cancel")
                        }
                    },
                    properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                )
            }
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(if (switchState.value) "Finalizar" else "Finish")
            }
        }
    }
}