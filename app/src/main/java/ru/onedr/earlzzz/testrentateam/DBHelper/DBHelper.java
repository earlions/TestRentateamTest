package ru.onedr.earlzzz.testrentateam.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public class DBHelper extends SQLiteOpenHelper {

    static final String CONTACTS_COLUMN_NAME = "firsName";
    static final String CONTACTS_COLUMN_LAST_NAME = "lastName";
    static final String CONTACTS_COLUMN_AVATAR_SRC = "avatarSrc";
    static final String CONTACTS_COLUMN_EMAIL = "email";
    private static final String CONTACTS_COLUMN_ID = "id";
    private static String DATABASE_NAME = "MyDBName.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, firsName text,lastName text,avatarSrc text,email text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void insertContact(List<Post> posts) {
        SQLiteDatabase db;
        int size =posts.size();
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int n=0;n<size;n++){
        contentValues.put(CONTACTS_COLUMN_NAME, posts.get(n).getFirsName());
        contentValues.put(CONTACTS_COLUMN_LAST_NAME, posts.get(n).getLastName());
        contentValues.put(CONTACTS_COLUMN_AVATAR_SRC, posts.get(n).getAvatarSrc());
        contentValues.put(CONTACTS_COLUMN_EMAIL, posts.get(n).getUserEmail());
        db.insert("contacts", null, contentValues);}
        db.close();
    }

    public List<Post> getData() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Post> mPosts = new ArrayList<>();
        String firstName;
        String lastName;
        String avataSrc;
        String email;


        Cursor CURSOR;
        int n=0;
        int m = getcount();


        for (n = 0; n <= m * m; n++) {

            CURSOR = db.rawQuery("select * from contacts where id=" + n + "", null);
            CURSOR.moveToFirst();
            if (CURSOR.getCount() > 0) {
                firstName = CURSOR.getString(CURSOR.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                lastName = CURSOR.getString(CURSOR.getColumnIndex(DBHelper.CONTACTS_COLUMN_LAST_NAME));
                avataSrc = CURSOR.getString(CURSOR.getColumnIndex(DBHelper.CONTACTS_COLUMN_AVATAR_SRC));
                email =  CURSOR.getString(CURSOR.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                mPosts.add(new Post(n,firstName,lastName, email,avataSrc));
                CURSOR.close();


        }
        }
        return mPosts;
    }

    public int getcount() {
        int COUNT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();
        COUNT = res.getCount();
        res.close();
        return COUNT;
    }


    int getset(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where title" + " like ?", new String[]{text});
        res.moveToFirst();
        int ttt;
        if (res.getCount() == 0)
            ttt = 0;
        else
            ttt = res.getInt(res.getColumnIndex(DBHelper.CONTACTS_COLUMN_ID));
        res.close();
        return ttt;
    }

    void deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

}