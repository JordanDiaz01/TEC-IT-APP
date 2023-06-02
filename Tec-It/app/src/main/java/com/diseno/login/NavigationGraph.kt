package com.diseno.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diseno.login.ui.viewModel.mainViewModel

class NavigationGraph {
}@Composable
fun NavigationGraph(
    navController: NavHostController,
    switchState: MutableState<Boolean>,
    viewModel: mainViewModel
) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, switchState,viewModel)
        }
        composable("correoOlv"){
            viewModel.setCurrentPage("correoOlv")
            olvcon(navController, switchState,viewModel)
        }
        composable("nuevaContraseña"){
            viewModel.setCurrentPage("nuevaContraseña")
            olvcon2(navController, switchState,viewModel)
        }
        composable("crearCuenta"){
            viewModel.setCurrentPage("crearCuenta")
            CrearCuenta(navController, switchState,viewModel)
        }
        composable("addUser"){
            viewModel.setCurrentPage("addUser")
            AddUser(navController, switchState,viewModel)
        }
        composable("addProductScreen"){
            viewModel.setCurrentPage("addProductScreen")
            AddProductScreen(navController, switchState,viewModel)
        }
        composable("menuPrincipal"){
            viewModel.setCurrentPage("menuPrincipal")
            MenuPrincipal(navController, switchState,viewModel)
        }
        composable("descripcionProducto"){
            viewModel.setCurrentPage("descripcionProducto")
            DescripcionProducto(navController, switchState,viewModel)
        }
        composable("cambioEstado"){
            if (viewModel.currentUser.value.isVendedor)
            {
                viewModel.setCurrentPage("addProductScreen")
                AddProductScreen(navController, switchState,viewModel)
            }
            else{
                viewModel.setCurrentPage("cambioEstado")
                CambioEstado(navController, switchState,viewModel)
            }
        }
    }
}