package at.htl.todo.model;

public class Todo {
    public Long userId;
    public Long id;
    public String title;
    public boolean completed;
    public String description;

    public Todo() {
        this.description = GetDefaultDescription();
    }

    public Todo(Long userId, Long id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.description = GetDefaultDescription();
    }

    public static String GetDefaultDescription(){
        return "no description available";
    }
}