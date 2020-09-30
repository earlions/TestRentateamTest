package ru.onedr.earlzzz.testrentateam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.onedr.earlzzz.testrentateam.DBHelper.DBHelper;
import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public class RepositoryImpl implements IRepository {


    @Override
    public Observable<List<Post>> getWrite(DBHelper dbHelper, List<Post> posts) {
        return Observable.create(observableEmitter -> {
            try {
                if (dbHelper.getcount()==0)
                dbHelper.insertContact(posts);
                else
                    posts.clear();
                observableEmitter.onNext(posts);
                observableEmitter.onComplete();
            } finally {
                observableEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Post>> getRead(DBHelper dbHelper) {
        return Observable.create(observableEmitter -> {

            List<Post> posts = new ArrayList<>();;
            try {
				posts.addAll(dbHelper.getData());

                observableEmitter.onNext(posts);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }
}
