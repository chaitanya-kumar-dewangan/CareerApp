package com.example.aspirehub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
//import androidx.compose.material.icons.filled.Work
//import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.aspirehub.models.Course

@Composable
fun CourseScreen(course: Course?) {
    course?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Card for Course Name and Description
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(4.dp),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Course Name
                            course.name?.let { name ->
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }


                        }
                    }
//                    new introduction
                    if (course.introduction.isNotEmpty()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "IntroDuction:-",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

//
                                course.introduction.forEach { introduction ->
                                    Row(modifier = Modifier.padding(start = 16.dp)) {
                                        Text(
                                            text = "-",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(end = 4.dp) // Add spacing between bullet and text
                                        )
                                        Text(
                                            text = introduction,
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                            }
                        }
                    }





                    // Card for Image
                    course.img?.let { imgUrl ->
                        if (imgUrl.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(4.dp),
//                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                AsyncImage(
                                    model = imgUrl,
                                    contentDescription = "${course.name} image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    // Card for Eligibility
                    course.eligibility?.let { eligibility ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MailOutline,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Eligibility: $eligibility",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

//                    entry path

                    if (course.entryPath.isNotEmpty()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "EntryPath:-",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

//
                                course.entryPath.forEach { entryPath ->
                                    Row(modifier = Modifier.padding(start = 16.dp)) {
                                        Text(
                                            text = "-",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(end = 4.dp) // Add spacing between bullet and text
                                        )
                                        Text(
                                            text = entryPath,
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                            }
                        }
                    }

                    if (course.institutions.isNotEmpty()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Institutions:-",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

//
                                course.institutions.forEach { institutions ->
                                    Row(modifier = Modifier.padding(start = 16.dp)) {
                                        Text(
                                            text = "-",
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.padding(end = 4.dp) // Add spacing between bullet and text
                                        )
                                        Text(
                                            text = institutions,
                                            style = MaterialTheme.typography.bodyMedium,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                            }
                        }
                    }

                    // Card for Job Roles
                    if (course.jobRoles.isNotEmpty()) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Job Roles:",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                course.jobRoles.forEach { jobRole ->
                                    Text(
                                        text = "- $jobRole",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }
                    }

                    course.workPlace?.let { workPlace ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Work Place: $workPlace",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    // Card for Salary
                    course.expectedSalary?.let { salary ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Average Salary: $salary",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    course.fees?.let { fees ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)),
                            shape = RoundedCornerShape(16.dp),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Fees: $fees",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    } ?: run {
        Text(
            text = "Course details are not available.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

