package ru.onedr.earlzzz.testrentateam.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public interface JSONPlaceHolderApi {
    @GET("/api/users")
public Observable<JsonObject> getAllPosts();
}
