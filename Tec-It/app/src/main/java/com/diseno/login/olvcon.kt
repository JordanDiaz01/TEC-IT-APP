package com.diseno.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun olvcon(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    val email = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

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
            /*Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(128.dp)
                    .clip(CircleShape)
            )*/
            Text(
                text = if (switchState.value) "Correo Electrónico" else "Email",
                modifier = Modifier.padding(top = 32.dp),
                //style = MaterialTheme.typography.h4
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it.toString() },
                label = { Text(if (switchState.value) "Correo Electrónico" else "Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.focusRequester(focusRequester).padding(top = 32.dp)

            )

            Button(onClick = {
                navController.navigate("nuevaContraseña")
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(if (switchState.value) "Siguiente" else "Next")
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}