package com.example.aspirehub

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aspirehub.models.Subject
import com.example.aspirehub.sealed.DataState.*
import com.example.aspirehub.viewmodels.MainViewModel
import okhttp3.Response


@Composable

fun PGMaths(/*navController: NavController*/) {
    val viewModel: MainViewModel = viewModel()
    val response by viewModel.response.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {




            when (response) {
                is Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Success -> {
                    ShowMathsPG((response as Success).data)
                }

                is Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (response as Failure).message)
                    }
                }

                Empty -> TODO()
                is Error -> TODO()
            }

        }
    }
}


@Composable
fun ShowMathsPG(subjects: List<Subject>) {
    LazyColumn {
        items(subjects) { each ->
            CardItem(subject = each)
        }
    }
}

@Composable
fun CardItem(subject: Subject) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp)
            .clickable {
                Toast
                    .makeText(context, subject.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
                    AsyncImage(model = subject.img
                        , contentDescription =null , modifier = Modifier.size(100.dp).clip(
                            RoundedCornerShape(20.dp)
                        ))
            Text(
                text = subject.name ?: "No Name Provided",  // Fallback text if Name is null
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
            )
        }
    }
}


