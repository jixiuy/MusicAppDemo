package com.example.musicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private UserDatabaseHelper dbHelper;


    public PlaylistDAO(Context context) {
        dbHelper = new UserDatabaseHelper(context);
    }

    // 插入新的歌单
    public long insertPlaylist(String name, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDatabaseHelper.COLUMN_NAME, name);
        values.put(UserDatabaseHelper.COLUMN_BIO, description);
        return db.insert(UserDatabaseHelper.TABLE_NAME, null, values);
    }

    // 获取所有歌单
    public List<User> getAllPlaylists() {
        List<User> playlistList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME));
                String bio = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_BIO));

                playlistList.add(new User( name, bio));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return playlistList;
    }

    // 删除歌单的方法
    public void deletePlaylist(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(UserDatabaseHelper.TABLE_NAME, UserDatabaseHelper.COLUMN_NAME + "=?", new String[]{String.valueOf(name)});
    }

    // 更新歌单信息
    public void updatePlaylist(String oldName, String newName, String newBio) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDatabaseHelper.COLUMN_NAME, newName);
        values.put(UserDatabaseHelper.COLUMN_BIO, newBio);
        db.update(UserDatabaseHelper.TABLE_NAME, values, UserDatabaseHelper.COLUMN_NAME + "=?", new String[]{oldName});
    }
}
