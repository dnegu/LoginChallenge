package com.dnegu.loginpruebatecnica.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dnegu.loginpruebatecnica.R
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain.GREETING_SCREEN
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true){
        delay(1000)
        navController.navigate(GREETING_SCREEN)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(
            fontSize = 24.sp,
            color = Color.White,
            text = "Welcome to LoginPT"
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSplash() {
    LoginPruebaTecnicaTheme {
        Splash()
    }
}