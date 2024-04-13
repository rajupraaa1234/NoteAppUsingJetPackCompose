package com.example.noteappusingjetpackcompose.notes_features.presentation.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.intelligencenoteapp.notes_features.data.domain.model.InvalidNoteException
import com.example.noteappusingjetpackcompose.R
import com.example.noteappusingjetpackcompose.notes_features.presentation.State.NoteState
import com.example.noteappusingjetpackcompose.notes_features.presentation.utils.NoteEvent


@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NoteEvent) -> Unit,
    context: Context
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onSubmitButtonClick(navController,onEvent,state,context)
            }) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.title.value, onValueChange = {
                    state.title.value = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = stringResource(id = R.string.title))
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(
                value = state.content.value, onValueChange = {
                    state.content.value = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = stringResource(id = R.string.description))
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }
    }

}

fun onSubmitButtonClick(
    navController: NavController,
    onEvent: (NoteEvent) -> Unit,
    state: NoteState,
    context: Context
) {
    try{
        if (state.title.value.isBlank()){
            throw InvalidNoteException("Please enter title")
        }
        else if (state.content.value.isBlank()){
            throw InvalidNoteException("Please enter description")
        }else{
            onEvent(
                NoteEvent.SaveNote(
                    title = state.title.value,
                    content = state.content.value
                )
            )
            navController.popBackStack()
        }
    }catch (e : InvalidNoteException){
        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
    }

}
