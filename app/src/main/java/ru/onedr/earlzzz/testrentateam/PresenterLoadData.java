package ru.onedr.earlzzz.testrentateam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.onedr.earlzzz.testrentateam.DBHelper.DBHelper;
import ru.onedr.earlzzz.testrentateam.recyclerview.Post;
import ru.onedr.earlzzz.testrentateam.retrofit.JSONPlaceHolderApi;
import ru.onedr.earlzzz.testrentateam.retrofit.NetworkService;

public class PresenterLoadData implements HomeView.Presenter{
    private HomeView.View mView;
    private DBHelper dbHelper;
    private boolean internet;
    final List<Post> mPostDB = new ArrayList<>();
    final List<Post> mPost = new ArrayList<>();

    public PresenterLoadData(HomeView.View mView, Context context) {
        this.mView = mView;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void onLoadBD() {



        NetworkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {


                    @Override
                    public void onError(Throwable e) {
                        onReadBDOffline();
                    }

                    @Override
                    public void onComplete() {
                        onLoadBDOffline(mPost);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject js) {
                        JsonObject jsonObject;
                        int size=  js.getAsJsonArray("data").size();
                        for (int n=0;n<size;n++){
                            jsonObject=js.getAsJsonArray("data").get(n).getAsJsonObject();
                            long id =jsonObject.get("id").getAsLong();
                            String firsName=jsonObject.get("first_name").getAsString();
                            String lastName=jsonObject.get("last_name").getAsString();
                            String email=jsonObject.get("email").getAsString();
                            String avatarSrc=jsonObject.get("avatar").getAsString();
                            Post post= new Post(id,firsName,lastName,email,avatarSrc);
                            mPost.add(post);}
                        mView.showData(mPost);


                    }
                });

    }
    private void onReadBDOffline(){
            new RepositoryImpl().getRead(dbHelper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Post>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(List<Post> posts) {
                            if (posts!=null)
                                mPostDB.addAll(posts);
                            mView.showData(mPostDB);
                            mView.showError("Нет подключения к сети");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            mView.showError("Ошибка при загрузки Базы данных");
                        }

                        @Override
                        public void onComplete() {
                            mView.showComplete();
                        }
                    });
    }

    private void onLoadBDOffline(List<Post> posts){
        new RepositoryImpl().getWrite(dbHelper, posts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                    }
                });


    }



}





