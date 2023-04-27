package com.dnegu.loginpruebatecnica.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.dnegu.loginpruebatecnica.R
import com.dnegu.loginpruebatecnica.data.model.response.NewUserResponse
import com.dnegu.loginpruebatecnica.data.model.response.UserResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.navigations.NavDestinationsMain
import com.dnegu.loginpruebatecnica.ui.base.AppComposable
import com.dnegu.loginpruebatecnica.ui.composables.BigTextScreen
import com.dnegu.loginpruebatecnica.ui.composables.ButtonWithColor
import com.dnegu.loginpruebatecnica.ui.screens.greeting.GreetingViewModel
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme

var firstTime = true

@Composable
fun LoginScreen(
    id: Int,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val user by viewModel.user.observeAsState(initial = User("","","",0,""))
    val loginUser by viewModel.loginUser.observeAsState(initial = NewUserResponse(0,""))
    viewModel.getUserById(id)
    AppComposable(
        viewModel = viewModel,
        content = {Login(user, loginUser, navController, viewModel)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(user: User, loginUser: NewUserResponse, navController: NavController, viewModel: LoginViewModel) {
    val context = LocalContext.current
    if(firstTime){ "firstTime"}
    else if(loginUser.token.isNotEmpty() && !firstTime) {
        firstTime = true
        navController.navigate("${NavDestinationsMain.LIST_USER_SCREEN}/${user.id}")
    } else {
        Toast.makeText(context, "Failed to login", Toast.LENGTH_SHORT).show()
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
                BigTextScreen("Log in")
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Gray.copy(alpha = 0.4f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ){
                    var password by rememberSaveable { mutableStateOf("") }
                    var passwordVisibility by remember { mutableStateOf(false) }
                    var isButtonVisible by remember { mutableStateOf(false) }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(user.avatar),
                                contentDescription = "Imagen",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = user.first_name + " " + user.last_name,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = user.email,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
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
                                        contentDescription = if (passwordVisibility) "Ocultar contraseña" else "Mostrar contraseña"
                                    )
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonWithColor(text = "Continue", {
                        firstTime = false
                        isButtonVisible = !isButtonVisible
                        viewModel.loginUser(user.email,password)
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    if(isButtonVisible){
                        ButtonWithColor(text = "Put correct values", {
                            viewModel.getUserById(4)
                            password = "cityslicka"
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
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
fun DefaultPreviewLogin() {
    LoginPruebaTecnicaTheme {
        Login(
            user = UserResponse.mock().data,
            loginUser = NewUserResponse.mock(),
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}