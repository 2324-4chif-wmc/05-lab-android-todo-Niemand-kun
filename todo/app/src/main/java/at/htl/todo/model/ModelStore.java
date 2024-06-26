package at.htl.todo.model;

import android.util.Log;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import at.htl.todo.util.store.Store;

@Singleton
public class ModelStore extends Store<Model>  {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos) {
        apply(model -> model.todos = todos);
    }

    public void selectTab(int tabIndex) {
        apply(model -> model.uiState.selectedTab = tabIndex);
    }

    public void selectTodoId(Long todoId){
        apply(model -> model.uiState.detailTodoId = todoId);
    }

    public void switchEditing(){
        apply(model -> model.uiState.isEditing = !model.uiState.isEditing);
    }

    public void switchEditingDescription(Long id){
        apply(model -> {
            model.uiState.isEditingDescription = !model.uiState.isEditingDescription;
            for(int i = 0; i < model.todos.length; i++){
                if(Objects.equals(model.todos[i].id, id)){
                    if(Objects.equals(model.todos[i].description, Todo.GetDefaultDescription())){
                        model.todos[i].description = "";
                    }else if(Objects.equals(model.todos[i].description, "")){
                        model.todos[i].description = Todo.GetDefaultDescription();
                    }
                }
            }
        });
    }

    public void setNewTitle(String title, Long id){
        apply(model -> {
            for(int i = 0; i < model.todos.length; i++){
                if(Objects.equals(model.todos[i].id, id)){
                    model.todos[i].title = title;
                }
            }
        });
    }

    public void setNewDescription(String description, Long id){
        apply(model -> {
            for(int i = 0; i < model.todos.length; i++){
                if(Objects.equals(model.todos[i].id, id)){
                    model.todos[i].description = description;
                }
            }
        });
    }

    public void deleteTodo(Long id){
        apply(model -> {
            Todo[] newTodoList = new Todo[model.todos.length - 1];
            int count = 0;

            for(int i = 0; i < model.todos.length; i++){
                if(Objects.equals(model.todos[i].id, id)){
                    continue;
                }
                newTodoList[count++] = model.todos[i];
                newTodoList[count - 1].id = (long) count;
            }

            model.todos = newTodoList;
            model.uiState.detailTodoId = -1L;
        });
    }

    public void setCheckboxTodo(Todo todo){
        Log.d("Checkbox done", todo.title);
        int id = (int) (todo.id - 1);
        apply(model -> model.todos[id].completed = !todo.completed);
    }

    public void addTodo(int tabIndex, String todoMassege){
        apply(model -> {
            Todo[] todos = new Todo[model.todos.length + 1];

            for(int i = 1; i < model.todos.length + 1; i++){
                todos[i] = model.todos[i - 1];
                todos[i].id++;
            }

            todos[0] = new Todo(1L, 1L, todoMassege, false);

            model.todos = todos;
            model.uiState.selectedTab = tabIndex;
        });
    }
}