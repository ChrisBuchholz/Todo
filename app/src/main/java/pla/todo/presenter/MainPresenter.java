package pla.todo.presenter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pla.todo.model.Todo;
import pla.todo.view.MainView;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class MainPresenter implements Presenter<MainView> {

    private MainView mainView;
    private Subscription subscription;

    public void loadTodo() {
        mainView.showProgressIndicator();

        subscription = Realm.getDefaultInstance()
                .where(Todo.class)
                .findAllSortedAsync("id")
                .<List<Todo>>asObservable()
                .subscribe(new Action1<RealmResults<Todo>>() {
                    @Override public void call(RealmResults<Todo> todos) {
                        if (!todos.isEmpty()) {
                            mainView.showTodo(todos);
                        } else {
                            mainView.showEmptyList();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Timber.e(throwable.getMessage());
                    }
                });
    }

    @Override public void attachView(MainView view) {
        this.mainView = view;
    }

    @Override public void detachView() {
        this.mainView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

}