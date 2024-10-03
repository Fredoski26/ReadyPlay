package com.example.readyplay.navigator

import android.content.DialogInterface.OnDismissListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.readyplay.SplashScreen
import com.example.readyplay.authentication.LoginScreen
import com.example.readyplay.dialogs.ScanScreen
import com.example.readyplay.features.moviedetails.MovieDetailsScreen
import com.example.readyplay.features.mycart.MyCart
import com.example.readyplay.features.scanner.Scanner
import com.example.readyplay.home.HomeScreen

@Composable
fun Navigation() {
    var showDialog by remember { mutableStateOf(false) }
   val onDismissRequest = {
        showDialog = false
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash") {
        composable("Login screen") {
            LoginScreen(navController = navController)
        }
        composable("Home screen") {
            HomeScreen(navController = navController)
        }

        composable("Scan screen") {
            Scanner(navController = navController)
        }

        composable("Splash") {
            SplashScreen(navController = navController)
        }


        composable("MyCart screen/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                }
            )
        ) {id->
            id.arguments?.getInt("id")?.let { ids->
                MyCart(id =ids, navController = navController)
            }

        }



        composable("MovieDetails screen/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                }
            )
        ) {id->
            id.arguments?.getInt("id")?.let { ids->
                MovieDetailsScreen(id =ids, navController = navController)
            }

        }
    }
}