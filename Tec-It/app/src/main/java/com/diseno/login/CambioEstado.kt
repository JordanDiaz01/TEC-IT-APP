package com.diseno.login


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.diseno.login.ui.viewModel.mainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CambioEstado(navController: NavHostController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    var isSeller by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { /* Acción a realizar al hacer clic en el botón de navegación */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }
            }
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(if (switchState.value) "¿Quieres convertirte en vendedor?" else "Do you want to become a salesperson?")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                RadioButton(
                    selected = isSeller,
                    onClick = { isSeller = true }
                )
                Text(if (switchState.value) "Si" else "Yes", modifier = Modifier.padding(start = 8.dp))
                RadioButton(
                    selected = !isSeller,
                    onClick = { isSeller = false },
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(text = "No", modifier = Modifier.padding(start = 8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Simular cambio de estado en la base de datos */ },
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(if (switchState.value) "Guardar" else "Save")
            }
        }
    }
}