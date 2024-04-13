package com.example.noteappusingjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.noteappusingjetpackcompose.notes_features.data.NoteDataBase
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.AddNoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.composable.NoteScreen
import com.example.noteappusingjetpackcompose.notes_features.presentation.mvvm.NoteViewModel
import com.example.noteappusingjetpackcompose.ui.theme.NoteAppUsingJetPackComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                NoteDataBase::class.java,
                name = "note.db"
            ).build()
        }
        val viewModel by viewModels<NoteViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory{
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
                NavHost(navController = navController, startDestination = "NoteScreen"){
                    composable("NoteScreen"){
                        NoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                    }
                    composable("AddNoteScreen"){
                        AddNoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppUsingJetPackComposeTheme {
        Greeting("Android")
    }
}