package com.diseno.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.diseno.login.ui.viewModel.mainViewModel


@Composable
fun MenuPrincipal(navController: NavController, switchState: MutableState<Boolean>,viewModel: mainViewModel){
    LazyColumn{
        items(viewModel.allPubli.value){ item ->
            if (!item.active){
                return@items
            }
            Row(modifier = Modifier
                .padding(30.dp)
                .border(
                    2.dp,
                    Color.Black,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .clickable { navController.navigate("descripcionProducto") },
                //Aqui va el componente hacia las vistas de los productos unitarios
                horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = item.titulo)
                    Text(text = item.descripcion.toString())
                }
                Column() {
                    AsyncImage(
                        model = item.imgpub,
                        contentDescription = item.descripcion,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

    }
}