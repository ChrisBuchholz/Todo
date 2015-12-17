package pla.todo;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pla.todo.model.Todo;
import timber.log.Timber;

public class TodoApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
        createTestData();

        Timber.plant(new Timber.DebugTree());
    }

    private void createTestData() {
        final Map<Integer, String> dummyData = new HashMap<>();
        dummyData.put(1, "Buy lamp");
        dummyData.put(2, "Draw a picture");
        dummyData.put(3, "Listen to music");

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override public void execute(Realm realm) {
                for (int i = 1; i <= dummyData.size(); i++) {
                    Todo t = realm.createObject(Todo.class);
                    t.setId(i);
                    t.setMessage(dummyData.get(i));
                }
            }
        });
        realm.close();
    }

}