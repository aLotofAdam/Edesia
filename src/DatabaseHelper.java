package com.example.projectedesia;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
    public static final String Database_Name = "recipe.db";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recipes (ID INTEGER PRIMARY KEY, Title TEXT, Url TEXT, Ingredients TEXT, Instructions TEXT, Time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  recipes");
        onCreate(db);
    }

    public ArrayList<HashMap<String,String>> getAllRecipe(){

        ArrayList<HashMap<String, String>> recipes;
        recipes = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipes", null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Title", cursor.getString(0));
                map.put("Url", cursor.getString(1));
                map.put("Ingredients", cursor.getString(1));
                map.put("Instructions", cursor.getString(1));
                map.put("Time", cursor.getString(1));
                recipes.add(map);
            } while (cursor.moveToNext());


        }return recipes;
    }

}



