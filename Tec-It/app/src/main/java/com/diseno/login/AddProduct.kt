package com.diseno.login

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    var productName by remember { mutableStateOf(TextFieldValue("")) }
    var productCost by remember { mutableStateOf(TextFieldValue("")) }
    var productDescription by remember { mutableStateOf(TextFieldValue("")) }
    var productImageUri by remember { mutableStateOf<Uri?>(null) }

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
            .padding(16.dp)
    ) {
        Text(
            text = "Agregar Producto",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Nombre del Producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productCost,
            onValueChange = { productCost = it },
            label = { Text("Costo del Producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("Descripción del Producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray)
            ) {
                if (productImageUri != null) {
                    Image(
                        painter = rememberImagePainter(productImageUri!!),
                        contentDescription = "Imagen del Producto",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Button(
                onClick = { /* Implementa el código para seleccionar una imagen */ },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text("Seleccionar Imagen")
            }
        }

        Button(
            onClick = {
                val gson = Gson()
//                val jsonStrin = "{\"\"}"
//                viewModel.publiPost()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Producto")
        }
    }
}}