package com.diseno.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diseno.login.ui.theme.LoginTheme
import androidx.lifecycle.ViewModelProvider
import com.diseno.login.ui.viewModel.mainViewModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: mainViewModel
    private var switchState = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        setContent {
            val navController = rememberNavController()
            LaunchedEffect(Unit){
                viewModel.getAllUsers("https://backend-app.revis8466.repl.co/user/all")
                viewModel.getAllPubli("https://backend-app.revis8466.repl.co/publi/all")
            }

            LaunchedEffect(viewModel.currentPage.value) {
                viewModel.setHideBottomNav(viewModel.currentPage.value == "login" || viewModel.currentPage.value == "correoOlv" || viewModel.currentPage.value == "crearCuenta")
            }
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                        BottomBar(navController = navController,viewModel = viewModel ,switchState = switchState)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController, viewModel: mainViewModel ,switchState: MutableState<Boolean>){
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Menu" ,
            route = "menuPrincipal",
            icon = Icons.Rounded.Home,
        ),
        BottomNavItem(
            name = "Chats",
            route = "crearCuenta",
            icon = Icons.Rounded.AddCircle,
        ),
        BottomNavItem(
            name = if (switchState.value) "Sale" else "Ventas",
            route = if (viewModel.currentUser.value.isVendedor)  "cambioEstado" else "addProductScreen",
            icon = Icons.Rounded.Settings,
        )
    )
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (!viewModel.hideBottomNav.value) {
                androidx.compose.material3.NavigationBar(
                    containerColor = Color.White,
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route

                        NavigationBarItem(
                            selected = selected,
                            onClick = { navController.navigate(item.route) },
                            label = {
                                Text(
                                    text = item.name,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = "${item.name} Icon",
                                )
                            }
                        )
                    }
                }
            }
        }
    ){Column{
        NavigationGraph(navController = navController as NavHostController, viewModel = viewModel ,switchState = switchState)
    }}
}
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)










