package com.example.aspirehub

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearchBar() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf(listOf<String>()) } // Example: search results

    Scaffold(
        topBar = {
            if (active) {
                SearchBar(
                    query = query,
                    onQueryChange = { newQuery ->
                        query = newQuery
                    },
                    onSearch = {
                        println("xxxxxxxxxxxx $it")
                        searchFirebase(query) { results ->
                            searchResults = results
                        }
                    },
                    active = active,
                    onActiveChange = { isActive ->
                        active = isActive
                    }
                ) {
                    LazyColumn {
                        items(searchResults) { result ->
                            Text(result)
                        }
                    }
                }
            } else {
                CenterAlignedTopAppBar(
                    title = { Text("App Title") },
                    actions = {
                        IconButton(onClick = { active = true }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Text("Main content goes here.")
            }
        }
    )
}

fun searchFirebase(query: String, onResult: (List<String>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("subject")
        .whereEqualTo("Arts", query) // Adjust for your criteria
        .get()
        .addOnSuccessListener { documents ->
            val results = documents.mapNotNull { it.getString("Arts") } // Extract names
            onResult(results)
        }
        .addOnFailureListener { exception ->
            Log.e("FirebaseSearch", "Error getting documents: ", exception)
            onResult(emptyList())
        }
}
