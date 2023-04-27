package com.dnegu.loginpruebatecnica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain
import com.dnegu.loginpruebatecnica.ui.screens.greeting.GreetingScreen
import com.dnegu.loginpruebatecnica.ui.screens.splash.SplashScreen
import com.dnegu.loginpruebatecnica.ui.screens.listusers.ListUsersScreen
import com.dnegu.loginpruebatecnica.ui.screens.login.LoginScreen
import com.dnegu.loginpruebatecnica.ui.screens.signup.SignUpScreen
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPruebaTecnicaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinationsMain.SPLASH_SCREEN,
                    ) {
                        composable(NavDestinationsMain.SPLASH_SCREEN) {
                            SplashScreen(navController)
                        }
                        composable(NavDestinationsMain.GREETING_SCREEN) {
                            GreetingScreen(navController)
                        }
                        composable("${NavDestinationsMain.LOGIN_SCREEN}/{id}") {
                            it.arguments?.getString("id")?.toInt()?.let { id ->
                                LoginScreen(id, navController)
                            }
                        }
                        composable("${NavDestinationsMain.SIGN_UP_SCREEN}/{email}") {
                            it.arguments?.getString("email")?.let { email ->
                                SignUpScreen(email, navController)
                            }
                        }
                        composable("${NavDestinationsMain.LIST_USER_SCREEN}/{id}") {
                            it.arguments?.getString("id")?.toInt()?.let { id ->
                                ListUsersScreen(id, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

