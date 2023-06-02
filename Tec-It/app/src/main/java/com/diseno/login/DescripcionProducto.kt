package com.diseno.login

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescripcionProducto(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel){
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            /*Image(
                painter = painterResource(R.drawable.producto_imagen),
                contentDescription = "Imagen del producto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )*/
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
            fun rememberImagePainter(imageUri: Uri): Painter {
                TODO("Not yet implemented")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nombre del producto")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Precio: $99.99")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Descripción breve del producto. Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed id lectus finibus, bibendum sem at, volutpat erat. Nunc accumsan leo vel metus rhoncus ultrices."
            )
        }
    }
}