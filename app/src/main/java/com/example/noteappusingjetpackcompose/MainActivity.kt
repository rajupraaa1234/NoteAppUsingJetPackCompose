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

import androidx.navigation.compose.rememberNavController
import com.example.noteappusingjetpackcompose.notes_features.presentation.Navigation.ComposeNavigation
import com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: NoteViewModel by viewModels()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                ComposeNavigation(viewModel, applicationContext)
            }
        }
    }
}
