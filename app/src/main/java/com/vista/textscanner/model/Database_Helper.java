package com.vista.textscanner.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database_Helper extends SQLiteOpenHelper {
    private static final String dbname = "textscanner.db";
    private static final int version = 1;
    private static final String tablename = "recognition";

    public Database_Helper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + tablename + " (id integer primary key autoincrement, " +
                "content text, " +
                "type text," +
                "datetime default current_timestamp, " +
                "imagepath text, " +
                "thumbpath text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String kategori, String konten, String path){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("content", konten);
        cv.put("type", kategori);
        cv.put("imagepath", path);
        cv.put("thumbpath", "");
        return db.insert(tablename, null, cv) != -1;
    }

    public void deleteData(List<Item> item){
        SQLiteDatabase database = this.getWritableDatabase();
        for(int i = 0; i<item.size(); i++){
            database.delete(tablename, "id=?", new String[]{item.get(i).getId()});
        }
        database.close();
    }

    public List<Item> getData(){
        String selectQuery;
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<>();
        selectQuery = "select * from " + tablename;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("content", cursor.getString(1));
                map.put("type", cursor.getString(2));
                map.put("datetime", cursor.getString(3));
                map.put("imagepath", cursor.getString(4));
                map.put("thumbpath", cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }


        List<Item> item = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            String id = wordList.get(i).get("id");
            String content = wordList.get(i).get("content");
            String type = wordList.get(i).get("type");
            String datetime = wordList.get(i).get("datetime");
            String imagepath = wordList.get(i).get("imagepath");
            String thumbpath = wordList.get(i).get("thumbpath");
            Item data = new Item(id, content, type, datetime, imagepath,thumbpath);
            item.add(data);
        }

        cursor.close();
        database.close();
        return item;
    }
}
