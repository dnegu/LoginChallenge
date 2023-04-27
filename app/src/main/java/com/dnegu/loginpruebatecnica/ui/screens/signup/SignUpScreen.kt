package com.dnegu.loginpruebatecnica.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnegu.loginpruebatecnica.R
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain.GREETING_SCREEN
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain.LOGIN_SCREEN
import com.dnegu.loginpruebatecnica.ui.base.AppComposable
import com.dnegu.loginpruebatecnica.ui.composables.BigTextScreen
import com.dnegu.loginpruebatecnica.ui.composables.ButtonWithColor
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme

var firstTime = true

@Composable
fun SignUpScreen(
    email: String,
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val responseCreate by viewModel.user.observeAsState(initial = NewUserResponse(-1, ""))
    AppComposable(
        viewModel = viewModel,
        content = {SignUp(email,responseCreate,navController,viewModel)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(email: String, response: NewUserResponse, navController: NavController, viewModel: SignUpViewModel) {
    val context = LocalContext.current
    var isButtonVisible by remember { mutableStateOf(false) }

    if(firstTime){ "firstime"}
    else if(response.id != 0 && !firstTime) {
        firstTime = true
        navController.navigate("$LOGIN_SCREEN/${response.id}")
    }
    else{
        isButtonVisible = true
        Toast.makeText(context, "Failed to create user", Toast.LENGTH_SHORT).show()
    }

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
                BigTextScreen("Sign Up")
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Gray.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ){
                    var emailText by remember { mutableStateOf(email) }
                    var password by rememberSaveable { mutableStateOf("") }
                    var passwordVisibility by remember { mutableStateOf(false) }

                    Text(
                        text = AnnotatedString.Builder("Looks like you don't have an account.\n" +
                                "Let's create a new account for \n").apply {
                            pushStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold)
                            )
                            append(email)
                            pop()
                        }.toAnnotatedString()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = emailText,
                        onValueChange = { emailText = it },
                        label = {
                            Text(
                                text = "Email",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            textColor = Color.Gray
                        ),
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisibility = !passwordVisibility },
                                content = {
                                    Icon(
                                        imageVector = if (passwordVisibility) Icons.Filled.Edit else Icons.Filled.Lock,
                                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                                    )
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = AnnotatedString.Builder("By selecting Agree and continue below.\n" +
                                "I agree to ").apply {
                            pushStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2E7D32)
                                ),
                            )
                            append("Terms of Service and Privacy Policy")
                            pop()
                        }.toAnnotatedString()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonWithColor(text = "Agree and continue", {
                        if(emailText.isEmpty() or password.isEmpty())
                            Toast.makeText(
                                context,
                                "Please fill in all the fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        else {
                            viewModel.registerUser(emailText, password)
                            firstTime = false
                        }
                    })
                    if(isButtonVisible){
                        Spacer(modifier = Modifier.height(16.dp))
                        ButtonWithColor(text = "Put correct values", {
                            emailText = "eve.holt@reqres.in"
                            password = "pistol"
                        })
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSignUp() {
    LoginPruebaTecnicaTheme {
        SignUp(
            email = "davidneyra@gmail.com",
            response = NewUserResponse.mock(),
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}