package com.dnegu.loginpruebatecnica.ui.screens.listusers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dnegu.loginpruebatecnica.R
import com.dnegu.loginpruebatecnica.data.model.response.UserListResponse
import com.dnegu.loginpruebatecnica.domain.model.User
import com.dnegu.loginpruebatecnica.ui.base.AppComposable
import com.dnegu.loginpruebatecnica.ui.theme.LoginPruebaTecnicaTheme
import com.dnegu.loginpruebatecnica.ui.theme.Purple40

@Composable
fun ListUsersScreen(
    id: Int,
    navController: NavController,
    viewModel: ListUsersViewModel = hiltViewModel()
) {
    val userList by viewModel.userList.observeAsState(initial = emptyList())
    viewModel.getUserList(1)
    AppComposable(
        viewModel = viewModel,
        content = {ListUsers(userList,navController, viewModel)}
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUsers(userList: List<User>, navController: NavController, viewModel: ListUsersViewModel) {
    var page by remember { mutableStateOf(1) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Other page") },
                shape = CircleShape,
                onClick = {
                    page = if(page == 1) 2 else 1
                    viewModel.getUserList(page)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "icon"
                    )
                },
                contentColor = Purple40
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
    { pv ->
        pv.toString()
        Column {
            Row {
                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter search parameter") },
                    modifier = Modifier.padding(8.dp, 5.dp)
                )
                Icon(
                    Icons.Filled.Search,
                    tint = Color.Green,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(2.dp, 16.dp)
                        .size(40.dp)
                        .clickable {
                            viewModel.searchWithParam(text)
                        }
                )
            }
            LazyColumn {
                items(userList) { user ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .shadow(10.dp)
                            .clickable {
                                //TODO Falta Implementacción de DETAIL_SCREEN para mostrar información detallada del User
                                //navController.navigate("${NavDestinationsMain.DETAIL_SCREEN}/${user.id}")
                            }
                            .testTag("CardClick"),
                    ) {
                        Row {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(user.avatar)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder),
                                error = painterResource(R.drawable.placeholder),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(150.dp, 150.dp)
                                    .padding(16.dp),
                                contentDescription = "ImageRecipeCard"
                            )
                            Column {
                                Text(
                                    text = user.id.toString() ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = (user.first_name + " " + user.last_name) ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = "Email: ${user.email}" ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewListUsers() {
    LoginPruebaTecnicaTheme {
        ListUsers(
            userList = UserListResponse.mock().data,
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}