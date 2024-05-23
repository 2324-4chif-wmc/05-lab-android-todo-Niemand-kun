package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo

@Composable
fun DisplayDescription(todo : Todo, store : ModelStore?, model : Model){
    HorizontalDivider()
    Row {
        Text(
            text = "Description:",
            style = MaterialTheme.typography.headlineSmall
        )
    }
    Row {
        if(model.uiState.isEditingDescription){
            TextField(
                value = todo.description,
                onValueChange = { store?.setNewDescription(it, todo.id) },
                textStyle = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )
        }else{
            Text(
                text = todo.description
            )
        }
    }

    Row{
        Button(
            onClick = {
                store?.switchEditingDescription(todo.id);
            },
        ) {
            Text(if(model.uiState.isEditingDescription){"save"}else{"edit"})
        }
    }
}