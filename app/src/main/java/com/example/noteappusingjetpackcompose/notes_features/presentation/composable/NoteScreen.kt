package com.example.noteappusingjetpackcompose.notes_features.presentation.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteappusingjetpackcompose.notes_features.presentation.State.NoteState
import com.example.noteappusingjetpackcompose.notes_features.presentation.utils.NoteEvent
import com.example.noteappusingjetpackcompose.ui.theme.Pink80

@Composable
fun NoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NoteEvent) -> Unit
) {
    val TAG = "NoteScreen"
    Scaffold(modifier = Modifier.background(Color.White),
        topBar = {
            Row(
                modifier = Modifier
                    .height(55.dp)
                    .background(Pink80)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notes App",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                IconButton(onClick = { onEvent(NoteEvent.SortNotes) }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.White
                    )
                }
            }
        },


        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.content.value = ""
                navController.navigate("AddNoteScreen")
            }, containerColor = Pink80 ,) {

                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }

    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Log.d(TAG, "NoteScreen: ${state.notes.size} ")
            items(state.notes.size) {
                NoteItem(
                    state = state,
                    index = it,
                    onEvent = onEvent
                )
            }
        }
    }

}

@Composable
fun SmallExample(state: NoteState) {
    SmallFloatingActionButton(
        onClick = {
            state.title.value = ""
            state.content.value = ""
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
}

@Composable
fun NoteItem(state: NoteState, index: Int, onEvent: (NoteEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Pink80)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = state.notes.get(index = index).title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.notes.get(index = index).content,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    }
}
