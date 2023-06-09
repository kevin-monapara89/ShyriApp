package com.kevin.shyriapp;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExternalDB extends SQLiteOpenHelper {

    static final String DB_NAME = "shayari.db";
    String DB_PATH = "";
    Context context;
    public ExternalDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) throws IOException {
        super(context, "shyari.db", null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        if (!isDataBaseExists()) {
            copyDataBase();
        }
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DB_NAME);

        FileOutputStream OutputStream = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[8 * 1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            OutputStream.write(buffer, 0 , length);
        }

        OutputStream.flush();
        OutputStream.close();
        myInput.close();
    }

    private boolean isDataBaseExists() {
        File dbfile = new File(DB_PATH + DB_NAME);
        return dbfile.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<ShyriModel> getShyri() {
        List<ShyriModel> modelList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String que = "SELECT * FROM shyri";
        Cursor cursor = database.rawQuery(que,null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            int id = cursor.getInt(0);
            String cat = cursor.getString(1);
            String shyri = cursor.getString(2);
            ShyriModel model = new ShyriModel(id, cat, shyri);
            modelList.add(model);
            cursor.moveToNext();
        }
        return  modelList;
    }
}
