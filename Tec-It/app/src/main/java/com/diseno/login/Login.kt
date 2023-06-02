package com.diseno.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import kotlin.concurrent.timer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    // Define los estados de los campos de correo electrónico y contraseña
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rotation by remember { mutableStateOf(0f) }
    var posta by remember { mutableStateOf(false) }
    var validacion by remember { mutableStateOf(false)}
    val showDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }

    LaunchedEffect(posta) {
        if (posta) {
            posta = false
            validacion = !fetchDataFromApi(email).isNullOrBlank()
        }
    }
    // Define si se muestra la contraseña en texto plano o enmascarada
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box( modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .rotate(rotation)
            .clickable { rotation += 90f }
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        // Muestra el texto "Iniciar sesión"
        Text(
            text = if (switchState.value) "Iniciar sesión" else "Log in",
            modifier = Modifier.padding(top = 32.dp),
            //style = MaterialTheme.typography.h4
        )

        // Muestra el campo de correo electrónico
        Text(
            text = if (switchState.value) "Correo electrónico" else "Email",
            modifier = Modifier.padding(top = 32.dp),
            //style = MaterialTheme.typography.subtitle1
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        // Muestra el campo de contraseña
        Text(
            text = if (switchState.value) "Contraseña" else "Password",
            modifier = Modifier.padding(top = 16.dp),
            //style = MaterialTheme.typography.subtitle1
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    //Icon(
                    //imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    //    contentDescription = if (passwordVisibility) "Ocultar contraseña" else "Mostrar contraseña"
                    //)
                }
            }
        )

        // Muestra el botón de "Iniciar sesión"
        Button(
            onClick = {
                if ((!email.isNullOrBlank() && !password.isNullOrBlank()) && viewModel.getUser(email,password)){
                    navController.navigate("menuPrincipal")
                }
                else{
                    showDialog.value = true
                }
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        {
            Text(if (switchState.value) "Iniciar sesión" else "Log in")
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "Error") },
                text = { Text(text = "El email o la contraseña no son correctos") },
                confirmButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text(text = "OK")
                    }
                }
            )
        }

        // Muestra el texto "¿Olvidaste tu contraseña?"
        IconButton(onClick = { navController.navigate("correoOlv")}, modifier = Modifier.widthIn(min = 500.dp) ) {
            Text(
                text = if (switchState.value) "¿Olvidaste tu contraseña?" else "Forget password?",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp),
                //style = MaterialTheme.typography.body2
            )
        }


        // Muestra el texto "¿No tienes cuenta?"
        IconButton(onClick ={ navController.navigate("crearCuenta") }, modifier = Modifier.widthIn(min = 500.dp) ) {
            Text(
                text = if (switchState.value) "¿No tienes cuenta?" else "Don't have an account?",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 16.dp),
                //style = MaterialTheme.typography.body2
            )
        }
        //SWITCH
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .offset(x = (160).dp, y = 1.dp),
            color = if (switchState.value) Color.White else Color.Gray,
            shape = CircleShape
        ) {
            Box(modifier = Modifier.testTag("switchContainer")) {
                IconButton(
                    onClick = { switchState.value = !switchState.value },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (switchState.value) Icons.Default.Person else Icons.Default.Person,
                        contentDescription = if (switchState.value) "Switch activado" else "Switch desactivado"
                    )
                }
            }

        }
    }
}

