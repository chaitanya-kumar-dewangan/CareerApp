package com.example.aspirehub

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.aspirehub.models.Subject
import com.example.aspirehub.viewmodels.MainViewModel

@Composable
fun InterestCard(subject: Subject, navController: NavController, viewModel: MainViewModel) {

    val context = LocalContext.current
    Card(colors = CardDefaults.cardColors(getRandomColorWithOpacity()),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(4.dp)
            .clickable {
//                Toast
//                    .makeText(context, subject.toString(), Toast.LENGTH_SHORT)
//                    .show()
            viewModel.interest.value = subject
                navController.navigate("subject")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(model = subject.img
                , contentDescription =null , modifier = Modifier
                    .size(100.dp)
                    .clip(

                        RoundedCornerShape(0.dp)
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