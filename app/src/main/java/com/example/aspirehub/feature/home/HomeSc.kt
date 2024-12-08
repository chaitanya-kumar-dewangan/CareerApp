package com.example.aspirehub.feature.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aspirehub.InterestCard
import com.example.aspirehub.R
import com.example.aspirehub.sealed.DataState
import com.example.aspirehub.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(

    viewModel: MainViewModel,
    navController: NavHostController
) {

    val res = viewModel.response.collectAsState().value

    when (res) {
        is DataState.Failure -> {

        }

        DataState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()

            }
        }

        is DataState.Success -> {
            var isSearchActive by remember { mutableStateOf(false) } // Shared state for search mode
            var query by remember { mutableStateOf("") } // Shared state for the search query
            Scaffold(


//                topBar = {
//                    TopAppBar(
////
//                        title = {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(22.dp)
//                                    .padding(8.dp)
//                                    .shadow(
//                                        4.dp,
//                                        shape = MaterialTheme.shapes.medium
//                                    ) // Optional shadow for elevation effect
//                                    .background(
//                                        MaterialTheme.colorScheme.surface,
//                                        shape = MaterialTheme.shapes.medium
//                                    ) // Surface color and rounded corners
//                                    .padding(8.dp), // Inner padding for content spacing
//                                contentAlignment = Alignment.Center
////                                modifier = Modifier.fillMaxWidth() // Make the Box fill the width of the TopAppBar
//                            ) {
//                                Text(
//                                    text = "SELECT YOUR INTEREST",
//                                    fontSize = 20.sp,
//                                    style = MaterialTheme.typography.titleMedium,
//                                    modifier = Modifier.align(Alignment.Center) // Center the text
//                                )
//                            }
//                        }
//                    )
//                },


                topBar = {
                    TopAppBar(
                        title = {
                            if (isSearchActive) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                                        .background(
                                            MaterialTheme.colorScheme.surface,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                        .padding(8.dp)
                                ) {
                                    TextField(
                                        value = query,
                                        onValueChange = { newQuery ->
                                            query = newQuery
                                            // Call your ViewModel's search function here
                                            // For example: viewModel.searchSubjects(newQuery)
                                        },
                                        placeholder = { Text("Search...") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(),
                                        textStyle = MaterialTheme.typography.bodyMedium,

                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = MaterialTheme.colorScheme.surface,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        )
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(22.dp)
                                        .padding(8.dp)
                                        .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                                        .background(
                                            MaterialTheme.colorScheme.surface,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                        .padding(8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "SELECT YOUR INTEREST",
                                        fontSize = 20.sp,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        },

                    )
                },
                bottomBar = {

                },


                content = { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues) // Apply padding from the Scaffold
                            .padding(horizontal = 16.dp, vertical = 16.dp), // Additional padding
                    ) {
                        items(res.data) { subject ->
                            InterestCard(
                                subject = subject,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            )

        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Something went wrong")
            }
        }
    }
}


