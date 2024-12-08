package com.example.aspirehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
@Composable
fun StudentsInterestPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Students Interest \n For Matrix Students",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        InterestButtons()
    }
}

@Composable
fun InterestButtons() {
    val interests = listOf(
        "Maths", "Reasoning", "Engineering", "Computer Science",
        "Chemistry", "Literature", "Teaching"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        for (interest in interests) {
            OvalButton1(interestName = interest)
        }
    }
}

@Composable
fun OvalButton1(interestName: String) {
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
            text = interestName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStudentsInterestPage() {
    StudentsInterestPage()
}