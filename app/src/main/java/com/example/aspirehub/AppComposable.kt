package com.example.aspirehub


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.aspirehub.feature.home.Home
import com.example.aspirehub.feature.me.Signinp.LoginScreen
import com.example.aspirehub.feature.me.Signupp.SignUp
import com.example.aspirehub.models.Degree
import com.example.aspirehub.searchbar.MyTopAppBar
import com.example.aspirehub.searchbar.YourViewModel
import com.example.aspirehub.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun App(yourViewModel: YourViewModel) {


    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            IconButton(onClick = {
                navController.navigate("home")
            }) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                navController.navigate("search") {
                    popUpTo("home") { inclusive = false } // Remove "home" from back stack
                    launchSingleTop = true // Avoid adding duplicate "search" screens
                    restoreState = true    // Restore the state of "search" if previously visited
                }
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }

            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                navController.navigate("profile") {
                    popUpTo("home") { inclusive = false } // Remove "home" from back stack
                    launchSingleTop = true // Avoid adding duplicate "search" screens
                    restoreState = true    // Restore the state of "search" if previously visited
                }
            }) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
            }

        }
    }) {


        val currentUser = FirebaseAuth.getInstance().currentUser
        val viewModel: MainViewModel = viewModel()
        val start = if (currentUser != null) "home" else "login"
        Column(modifier = Modifier.padding(it)) {


            NavHost(navController = navController, startDestination = start) {

                composable("login") {
                    LoginScreen(navController)
                }
                composable("signup") {
                    SignUp(navController)
                }
                composable("search") {
                    MyTopAppBar(viewModel = yourViewModel)
                }

                composable("profile") {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        Text(text = "Profile")
                    }
                }

                composable("home") {
                    Home(viewModel, navController)
                }   
                composable("subject") {
                    SubjectList(viewModel, navController)

                }
                composable("degree") {
                    DegreeScreen(viewModel, navController)
                }
                composable("course") {
                    val course = viewModel.option.value
                    CourseScreen(course)
//                LazyColumn {
//                    item {
//
//                        if (course != null) {
//                            Text(text = course.toString())
//                        }
//                        if (course != null) {
//                            AsyncImage(model = course.img.toString(), contentDescription =null )
//                        }
//                    }
//                }


                }
            }
        }
    }

}

@Composable
private fun DegreeScreen(
//    list of courses page

    viewModel: MainViewModel,
    navController: NavHostController
) {
    val degree = viewModel.degree.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 45.dp)

//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                Text(
                    text = "LIST OF COURSES", // Use the label parameter to set your text
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
            }
        }

        if (degree != null) {
            items(degree.options!!) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth() // Fill the width of the screen
                        .height(80.dp) // Set height to 50dp
                        .padding(horizontal = 8.dp, vertical = 8.dp), // Horizontal padding
//                        .elevation(4.dp), // Apply elevation for shadow effect
                    onClick = {
                        viewModel.option.value = it
                        navController.navigate("course") // Navigate to "course" screen
                    },
//                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface) // Set card background color from Material Theme

                    colors = CardDefaults.cardColors(getRandomColorWithOpacity()),
                )
                {
                    Text(
                        text = it.name.toString(), // Assuming 'name' is a property of the object 'it'
                        fontSize = 20.sp, // Font size adjusted to fit the 50dp card height
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), // Body style with bold weight
                        color = MaterialTheme.colorScheme.onSurface, // Text color from Material Theme (onSurface color)
                        modifier = Modifier
//                            .align(Alignment.Center) // Center the text in the card
                            .padding(8.dp) // Padding around the text to avoid it touching the card's edges
                    )
                }
            }
//                Card(onClick = {
//                    viewModel.option.value = it
//                    navController.navigate("course")
//                }) {
//                    Text(text = it.name.toString())
//                }
        }
    }
}


@Composable
fun SubjectList(viewModel: MainViewModel, navController: NavHostController) {
//    category of education page

    val degreeList = viewModel.interest.collectAsStateWithLifecycle().value?.degree!!
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                Text(
                    text = "CATEGORY OF EDUCATION", // Use the label parameter to set your text
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
            }
        }

        items(degreeList) {
            CourseCard(degree = it) {
                viewModel.degree.value = it
                navController.navigate("degree")
            }
        }


    }
}

@Composable
fun CourseCard(degree: Degree, onClick: () -> Unit) {


    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(getRandomColorWithOpacity()),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = degree.img, contentDescription = null, modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
            )
            Text(
                text = degree.name ?: "No Name Provided",  // Fallback text if Name is null
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
            )
        }

    }


}



