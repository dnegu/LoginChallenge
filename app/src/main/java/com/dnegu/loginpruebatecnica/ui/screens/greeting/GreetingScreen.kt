package com.dnegu.loginpruebatecnica.ui.screens.greeting

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnegu.loginpruebatecnica.R
import com.dnegu.loginpruebatecnica.data.model.response.UserListResponse
import com.dnegu.loginpruebatecnica.data.model.response.UserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain.LOGIN_SCREEN
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain.SIGN_UP_SCREEN
import com.dnegu.loginpruebatecnica.ui.base.AppComposable
import com.dnegu.loginpruebatecnica.ui.composables.BigTextScreen
import com.dnegu.loginpruebatecnica.ui.composables.ButtonWhiteIcon
import com.dnegu.loginpruebatecnica.ui.composables.ButtonWithColor
import com.dnegu.loginpruebatecnica.ui.composables.SeparatorWithTextOptional
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme
import com.dnegu.loginpruebatecnica.util.isValidEmail

@Composable
fun GreetingScreen(
    navController: NavController,
    viewModel: GreetingViewModel = hiltViewModel()
) {
    viewModel.getAllUserList()
    AppComposable(
        viewModel = viewModel,
        content = { Greeting(navController = navController, viewModel) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    navController: NavController,
    viewModel: GreetingViewModel
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black,
        contentColor = Color.White,
        content = {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Background image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                BigTextScreen("Hi!")
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Gray.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    var emailText by remember { mutableStateOf("") }

                    TextField(
                        value = emailText,
                        onValueChange = { emailText = it },
                        label = {
                            Text(
                                text = "Email",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(0.dp, 8.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonWithColor(text = "Continue", {
                        if (emailText.isValidEmail()) {
                            val user = viewModel.searchEmailInList(emailText)
                            if (user.id != 0)
                                navController.navigate("${LOGIN_SCREEN}/${user.id}")
                            else
                                navController.navigate("${SIGN_UP_SCREEN}/${emailText}")
                        } else {
                            Toast.makeText(
                                context,
                                "Please enter a valid email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    SeparatorWithTextOptional("or")
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonWhiteIcon(
                        text = "Continue with Facebook",
                        onclick = {
                            Toast.makeText(context, "Ingresando con Facebook", Toast.LENGTH_SHORT)
                                .show()
                        },
                        icon = R.drawable.ic_facebook,
                        contentDescription = "Facebook Icon"
                    )
                    ButtonWhiteIcon(
                        text = "Continue with Google",
                        onclick = {
                            Toast.makeText(context, "Ingresando con Google", Toast.LENGTH_SHORT)
                                .show()
                        },
                        icon = R.drawable.ic_google,
                        contentDescription = "Google Icon"
                    )
                    ButtonWhiteIcon(
                        text = "Continue with Apple",
                        onclick = {
                            Toast.makeText(context, "Ingresando con Apple", Toast.LENGTH_SHORT)
                                .show()
                        },
                        icon = R.drawable.ic_apple,
                        contentDescription = "Apple Icon"
                    )
                    Row() {
                        Text(
                            text = "Don't have an account?",
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            modifier = Modifier.clickable { }
                        )
                        Text(
                            text = " Sign up",
                            color = Color(0xFF2E7D32),
                            fontSize = 16.sp,
                            modifier = Modifier.clickable { }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Forgot your password?",
                        color = Color(0xFF2E7D32),
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewGreeting() {
    LoginPruebaTecnicaTheme {
        Greeting(
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}