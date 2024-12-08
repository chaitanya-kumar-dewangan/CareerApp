package com.example.aspirehub.searchbar

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.aspirehub.models.Subject
import com.example.aspirehub.sealed.DataState

@Composable
fun SubjectList(viewModel: YourViewModel) {
    val subjectsState by viewModel.response.observeAsState(initial = DataState.Empty)

        when (subjectsState) {
        is DataState.Success<*> -> {
            val subjects = (subjectsState as DataState.Success<List<Subject>>).data
            LazyColumn {
                items(subjects) { subject ->
                    Text(subject.toString() ?: "No Name")
                }
            }
        }
        is DataState.Error -> {
            Text("Error: ${(subjectsState as DataState.Error).exception.message}")
        }
        DataState.Empty -> {
            Text("No subjects found.")
        }
        DataState.Loading -> {
            CircularProgressIndicator()
        }

        is DataState.Failure -> TODO()
    }
}
