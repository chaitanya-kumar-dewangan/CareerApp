package com.example.aspirehub


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun StudentsStreamPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Student's Stream \n (For Post Matrix Students)",

            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        StreamButtons()
    }
}

@Composable
fun StreamButtons() {
    val streams = listOf(
        "Maths", "Science", "Art", "Agriculture",
        "Commerce", "Computer Applications"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        for (stream in streams) {
            OvalButton2(streamName = stream)
        }
    }
}

@Composable
fun OvalButton2(streamName: String) {
    Button(
        onClick = { /* TODO: Handle button click */ },
        modifier = Modifier
            .width(250.dp)
            .height(60.dp),
        shape = RoundedCornerShape(50),  // Oval shape
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Text(
            text = streamName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudentsStreamPage() {
    StudentsStreamPage()
}