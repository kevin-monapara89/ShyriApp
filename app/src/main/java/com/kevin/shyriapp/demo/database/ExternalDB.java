package com.kevin.shyriapp.demo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kevin.shyriapp.ShyriModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExternalDB extends SQLiteOpenHelper {
    static final String DB_NAME = "shayari.db";
    String DB_PATH ;
    String dbname;
    Context context;
    public ExternalDB(@Nullable Context context, @Nullable String name, int version)  {
        super(context, name, null, version);
        this.dbname = name;
        this.context = context;

        this.DB_PATH = "/data/data/" + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void CheckDb() {
        SQLiteDatabase checkdb = null;
        String filPath = DB_PATH + dbname;
        try {
            checkdb = SQLiteDatabase.openDatabase(filPath,null,0);
        }catch (Exception e ) {
            e.printStackTrace();
        }
        if (checkdb != null){
            Toast.makeText(context, "Database Already Exist", Toast.LENGTH_SHORT).show();
        }else {
            CopyDatabase();
        }
    }

    public void CopyDatabase() {
        this.getReadableDatabase();

        try {
            InputStream inputStream = context.getAssets().open(dbname);
            OutputStream outputStream = new FileOutputStream(DB_PATH + dbname);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("CopyDb","DB Copied--------------");
    }

    public void OpenDatabase() {
        String filepath = DB_PATH + dbname;
        SQLiteDatabase.openDatabase(filepath,null,0);
    }

    public List<ShyriModel> getShyri() {
        List<ShyriModel> modelList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String que = "SELECT * FROM shayari";
        Cursor cursor = database.rawQuery(que,null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            int id = cursor.getInt(0);
            int cat_id = cursor.getInt(1);
            String shyri = cursor.getString(2);
            ShyriModel model = new ShyriModel(id, cat_id, shyri);
            modelList.add(model);
            cursor.moveToNext();
        }
        return  modelList;
    }
}
