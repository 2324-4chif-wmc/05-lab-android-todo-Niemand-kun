package at.htl.todo.ui.layout

import android.icu.lang.UCharacter.LineBreak
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo
import at.htl.todo.ui.theme.TodoTheme
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Composable
fun TodosScreen(model: Model, store: ModelStore?, modifier: Modifier = Modifier) {
    val todos = model.todos;
    val detailTodo = model.uiState.detailTodo

    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        if(detailTodo == null) {
        items(todos.size) { index ->
                TodoRow(todo  = todos[index], store = store)
                HorizontalDivider()
        }
        }else{
            item{
                ShowDetail(model.uiState.detailTodo, store = store)
            }
        }
    }
}

@Composable
fun TodoRow(todo: Todo, store: ModelStore?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = todo.title,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = todo.id.toString(),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = todo.completed,
            onCheckedChange = { store?.setCheckboxTodo(todo) }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                store?.selectedTodo(todo)
            },
        ) {
            Text("Details")
        }
    }
}

@Composable
fun ShowDetail(todo : Todo, store: ModelStore?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Todo: ${todo.title}",
            style = MaterialTheme.typography.headlineSmall
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Id: ${todo.id}",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val text = if(todo.completed){
            "TASK HAS BEEN COMPLETED"
        }else{
            "TASK HAS NOT BEEN COMPLETED"
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                store?.selectedTodo(null)
            },
        ) {
            Text("Back")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                store?.setCheckboxTodo(todo)
            },
        ) {
            Text(if(todo.completed){"Undo"}else{"Complete"})
        }
    }
}