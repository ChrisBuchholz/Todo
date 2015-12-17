package pla.todo.view;

import java.util.List;

import pla.todo.model.Todo;

public interface MainView extends BaseView {
    void showTodo(List<Todo> todoList);
    void showEmptyList();
    void showProgressIndicator();
}