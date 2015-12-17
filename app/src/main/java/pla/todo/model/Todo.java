package pla.todo.model;

import io.realm.RealmObject;

public class Todo extends RealmObject {
    private int id;
    private String message;

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}