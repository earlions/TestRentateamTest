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


    public PresenterLoadData(HomeView.View mView, Context context, FragmentActivity fragmentActivity) {
        this.mView = mView;
        this.dbHelper = new DBHelper(context);
        ConnectivityManager cm = (ConnectivityManager) fragmentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        internet = netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onLoadBD() {
        final List<Post> mPost = new ArrayList<>();
        final List<Post> mPostDB = new ArrayList<>();
        if (internet){

        NetworkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.offLoad();
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
                        if (dbHelper.getcount()==0)
                            dbHelper.insertContact(mPost);


                        mView.showText(mPost);
                    }
                });}else
        {
            mPostDB.addAll(dbHelper.getData());
            mView.offLoad();
            mView.showText(mPostDB);
        }

    }



}
