package com.example.aspirehub.searchbar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirehub.models.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(viewModel: YourViewModel) {
    var isSearchActive by remember { mutableStateOf(false) } // To toggle search bar visibility
    var query by remember { mutableStateOf("") } // To store the search query
    val resultList = remember { mutableStateOf<List<Subject>>(emptyList()) } // To store the search results
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                        .padding(8.dp)
                        .shadow(
                            4.dp,
                            shape = MaterialTheme.shapes.medium
                        ) // Optional shadow for elevation effect
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.medium
                        ) // Surface color and rounded corners
                        .padding(8.dp), // Inner padding for content spacing
                    contentAlignment = Alignment.Center
                ) {
                    if (isSearchActive) {
                        // Search bar visible
                        TextField(
                            value = query,
                            onValueChange = { newQuery ->
                                query = newQuery
                                // Call the view model to fetch data based on the search query
                                viewModel.searchSubjects(newQuery) {
                                    println("xxxxxxxxxxxxxxxxxxxxxxx000")
                                    println(it.size)
                                    println("xxxxxxxxxxxxxxxxxxxxxxx000")
                                    resultList.value = it
                                }

                            },
                            placeholder = { Text("Search...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search Icon")
                            },
                            trailingIcon = {
                                IconButton(onClick = { isSearchActive = false }) {
                                    Icon(Icons.Default.Close, contentDescription = "Close Search")
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    } else {
                        // Normal title displayed
                        Text(
                            text = "SELECT YOUR INTEREST",
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(Alignment.Center) // Center the text
                        )
                    }
                }
            },
            actions = {
                if (!isSearchActive) {
                    // Search icon to toggle search bar visibility
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }
        )
    }, floatingActionButton = {
        
    },

        ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            items(resultList.value.size){
                Text(resultList.value[it].toString())
            }
        }
    }

}
