# Todo
This repository (and Android app) was solely created to try out the new support for RxJava in Realm.

## Discoveries
At first, I tried to observe on Schedulers.io and subscribe on AndroidSchedulers.mainThread(). That was before I remembered that Realm objects has to be handled on the same thread. This means you can't use subscribeOn. More about it [here](https://github.com/realm/realm-java/issues/1208).

### About
The app shows a todo list. Remember that you shouldn't use this app. It's just a sample app

### Libraries used
* AppCompat, CardView and RecyclerView
* Realm
* RxJava and RxAndroid
* ButterKnife
* Timber

### Things to come
* Add todo feature
* Delete todo feature
* Edit todo feature
* Rewrite/refactor from Java to Kotlin
