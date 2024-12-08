package com.example.aspirehub




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirehub.R
//@Preview
@Composable
fun MathsGraduationCoursePage() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Blurred image background
        Image(
            painter = painterResource(id = R.drawable.aryabhatt), // Use the image from drawable
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .blur(16.dp), // Apply blur effect only on the background
            contentScale = ContentScale.Crop // Scale the image to fill the box
        )

        // Content layer (cards and text) without blur
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Maths Graduation Course",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Category 1: B.Sc. Courses
                item { CategoryTitle(title = "Bachelor of Science (B.Sc.) Courses") }
                items(bscCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 2: B.Tech./B.E. Courses
                item { CategoryTitle(title = "Bachelor of Technology (B.Tech.) / Bachelor of Engineering (B.E.)") }
                items(btechCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 3: Bachelor of Arts (B.A.) Courses
                item { CategoryTitle(title = "Bachelor of Arts (B.A.) Courses") }
                items(baCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 4: Actuarial Science and Professional Courses
                item { CategoryTitle(title = "Actuarial Science and Professional Courses") }
                items(professionalCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 5: Integrated Courses
                item { CategoryTitle(title = "Integrated Courses") }
                items(integratedCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 6: Bachelor of Computer Applications (BCA)
                item { CategoryTitle(title = "Bachelor of Computer Applications (BCA)") }
                items(bcaCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 7: Diploma Courses
                item { CategoryTitle(title = "Diploma Courses Related to Maths") }
                items(diplomaCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 8: Special Courses in Pure and Applied Mathematics
                item { CategoryTitle(title = "Special Courses in Pure and Applied Mathematics") }
                items(specialCourses) { course ->
                    CourseCard(courseName = course)
                }

                // Category 9: Career-Oriented Courses
                item { CategoryTitle(title = "Career-Oriented Courses for Maths Students") }
                items(careerCourses) { course ->
                    CourseCard(courseName = course)
                }
            }
        }
    }
}

@Composable
fun CategoryTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun CourseCard(courseName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)), // Light blue color for the card
        elevation = CardDefaults.cardElevation(4.dp) // Slight elevation for a subtle 3D effect
    ) {
        Text(
            text = courseName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

// Data for courses
val bscCourses = listOf(
    "B.Sc. Mathematics", "B.Sc. Mathematics (Honours)", "B.Sc. Applied Mathematics",
    "B.Sc. Statistics", "B.Sc. Actuarial Science", "B.Sc. Computer Science",
    "B.Sc. Data Science", "B.Sc. Economics", "B.Sc. Physics", "B.Sc. Operational Research"
)

val btechCourses = listOf(
    "B.Tech./B.E. in Computer Science and Engineering (CSE)",
    "B.Tech./B.E. in Information Technology (IT)",
    "B.Tech./B.E. in Electrical Engineering",
    "B.Tech./B.E. in Electronics and Communication Engineering (ECE)",
    "B.Tech./B.E. in Mechanical Engineering",
    "B.Tech./B.E. in Civil Engineering",
    "B.Tech./B.E. in Data Science",
    "B.Tech./B.E. in Artificial Intelligence and Machine Learning",
    "B.Tech./B.E. in Aerospace Engineering"
)

val baCourses = listOf(
    "B.A. in Mathematics", "B.A. in Economics", "B.A. in Statistics"
)

val professionalCourses = listOf(
    "Bachelor of Actuarial Science", "Chartered Financial Analyst (CFA)"
)

val integratedCourses = listOf(
    "Integrated M.Sc. in Mathematics", "Integrated M.Sc. in Mathematics and Computing",
    "Integrated M.Sc. in Statistics"
)

val bcaCourses = listOf(
    "BCA (Bachelor of Computer Applications)"
)

val diplomaCourses = listOf(
    "Diploma in Mathematical Finance", "Diploma in Applied Statistics", "Diploma in Actuarial Science"
)

val specialCourses = listOf(
    "B.Sc. in Mathematical Sciences", "B.Sc. in Applied Mathematics and Computing"
)

val careerCourses = listOf(
    "B.Sc. Financial Mathematics", "B.Sc. Quantitative Economics", "B.Sc. Bioinformatics"
)

@Preview(showBackground = true)
@Composable
fun PreviewMathsGraduationCoursePage() {
    MathsGraduationCoursePage()
}
