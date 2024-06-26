package com.example.noteappusingjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteappusingjetpackcompose.notes_features.data.DataBaseBuilder
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.AddNoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.NoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm.NoteViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { DataBaseBuilder(applicationContext) }

        val viewModel by viewModels<NoteViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return NoteViewModel(database.noteDao) as T
                    }
                }
            }
        )
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                val state by viewModel.state
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "NoteScreen") {
                    composable("NoteScreen") {
                        NoteScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable("AddNoteScreen") {
                        AddNoteScreen(
                            state = state,
                            navController = navController,
                            onEvent = viewModel::onEvent,
                            context = applicationContext
                        )
                    }
                }

            }
        }
    }
}
