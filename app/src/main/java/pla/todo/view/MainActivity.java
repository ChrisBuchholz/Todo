package pla.todo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pla.todo.R;
import pla.todo.TodoAdapter;
import pla.todo.model.Todo;
import pla.todo.presenter.MainPresenter;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private List<Todo> todoList;
    private TodoAdapter todoAdapter;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.rvTodo) RecyclerView rvTodo;
    @Bind(R.id.tvEmpty) TextView tvEmpty;
    @Bind(R.id.progressbar) ProgressBar progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter();
        presenter.attachView(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTodo.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Snackbar.make(view, "Add", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        todoAdapter = new TodoAdapter();
        rvTodo.setAdapter(todoAdapter);

        presenter.loadTodo();
    }

    @Override public void showTodo(List<Todo> todoList) {
        hideProgressBar();

        todoAdapter.setTodoList(todoList);
        todoAdapter.notifyDataSetChanged();

        tvEmpty.setVisibility(View.GONE);
        rvTodo.setVisibility(View.VISIBLE);
    }

    @Override public void showEmptyList() {
        hideProgressBar();
        tvEmpty.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override public Context getContext() {
        return getApplicationContext();
    }

}