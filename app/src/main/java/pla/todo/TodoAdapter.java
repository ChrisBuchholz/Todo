package pla.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pla.todo.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList;

    public TodoAdapter() {
        this.todoList = Collections.emptyList();
    }

    @Override public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_todo, parent, false);
        return new TodoViewHolder(v);
    }

    @Override public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo t = todoList.get(position);

        if (t != null) {
            holder.tvTodo.setText(t.getMessage());
        }
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_todo) TextView tvTodo;

        public TodoViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}