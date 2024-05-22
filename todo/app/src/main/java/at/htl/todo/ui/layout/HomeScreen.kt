package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.TodoService

@Composable
fun HomeScreen(model: Model, store: ModelStore?) {
    val todos = model.todos;
    var newTodoText by remember { mutableStateOf("") };

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Text(
                text = "Welcome Home!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier
            .align(Alignment.Start)
            .padding(16.dp)) {
            Text(
                text = "Leon Leeb",
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text("${todos.size} Todos have been loaded")

            Text("${todos.count { it.completed }} Todos are completed")
        }

        Column(modifier = Modifier
            .align(Alignment.Start)
            .padding(16.dp)) {

            OutlinedTextField(
                value = newTodoText,
                onValueChange = { newText -> newTodoText = newText },
                label = { Text("New To-Do") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    store?.selectTab(1);
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add To-Do")
            }
        }
    }
}
