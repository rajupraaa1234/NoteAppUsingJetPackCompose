package com.example.noteappusingjetpackcompose.notes_features.presentation.Navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.AddNoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.NoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm.NoteViewModel

@Composable
fun ComposeNavigation(viewModel: NoteViewModel, applicationContext: Context) {
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