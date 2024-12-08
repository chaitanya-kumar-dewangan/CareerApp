package com.example.aspirehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aspirehub.searchbar.YourViewModel
import com.example.aspirehub.ui.theme.AspireHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: YourViewModel = viewModel()
            AspireHubTheme {

//                Column {
//                    MyTopAppBar(viewModel = viewModel)
////                    SubjectList(viewModel = viewModel)  // This will show the list of subjects based on search
//                }
                App(yourViewModel = viewModel)
//                TopAppBarWithSearchBar()



            }
        }
    }
}