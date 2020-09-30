package ru.onedr.earlzzz.testrentateam;


import java.util.List;

import io.reactivex.Observable;
import ru.onedr.earlzzz.testrentateam.DBHelper.DBHelper;
import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public interface IRepository {
	Observable<List<Post>> getWrite(DBHelper dbHelper, List<Post> posts);
	Observable<List<Post>> getRead(DBHelper dbHelper);
	
}
