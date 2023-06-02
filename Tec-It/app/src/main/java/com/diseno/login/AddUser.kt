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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diseno.login.ui.viewModel.mainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUser(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    Scaffold(topBar = {
        TopAppBar(title = { Text("") },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.widthIn(min = 80.dp)) {
                    Text(if (switchState.value)"Volver" else "Back")
                }
            })
    }) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agregar Foto de Perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberImagePainter(imageUri!!),
                    contentDescription = "Imagen de Perfil",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(CircleShape)
                )
            } else {
                Text(
                    text = "Previsualización de la Foto de Perfil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Button(
                onClick = { /**/ },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text("Seleccionar Foto")
            }
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text("Finalizar")
            }
        }
    }
}}

fun rememberImagePainter(imageUri: Uri): Painter {
    TODO("Not yet implemented")
}
