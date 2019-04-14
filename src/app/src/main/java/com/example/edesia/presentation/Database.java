package com.example.edesia.presentation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "recipes.db";
    private static final int DB_VERSION = 1;


    public Database(Context context) {
        super(context, DB_NAME, context.getExternalFilesDir(null).getAbsolutePath(),null, DB_VERSION);
    }
    //function get all recipes
    public List<Recipe> getRecipes() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions","Picture"};
        String table_name = "recipes";

        qb.setTables(table_name);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setURL(cursor.getString(cursor.getColumnIndex("ID")));
                recipe.setURL(cursor.getString(cursor.getColumnIndex("URL")));
                recipe.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                recipe.setPrepTime(cursor.getString(cursor.getColumnIndex("PrepTime")));
                recipe.setTotalTime(cursor.getString(cursor.getColumnIndex("TotalTime")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("Ingredients")));
                recipe.setInstructions(cursor.getString(cursor.getColumnIndex("Instructions")));
                recipe.setURL(cursor.getString(cursor.getColumnIndex("Picture")));

                result.add(recipe);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //function to get all recipe titles

    public List<String> getTitles(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Title"};
        String table_name = "recipes";

        qb.setTables(table_name);
        //will query like select from where %patterm%
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Title")));

            } while (cursor.moveToNext());
        }
        return result;
    }


    public List<String> picture() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Picture"};
        String table_name = "recipes";
        qb.setTables(table_name);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Picture")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    //this function may have to be changed to return if keyword is in ingredient list
    public List<Recipe> getRecipebyName(String Title) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions"};
        String table_name = "recipes";
        qb.setTables(table_name);
        //will query select recipes from recipe database  where recipe is like %patterm%
        Cursor cursor = qb.query(db, sqlSelect, "Title LIKE ?", new String[]{"%" + Title + "%"}, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setURL(cursor.getString(cursor.getColumnIndex("ID")));
                recipe.setURL(cursor.getString(cursor.getColumnIndex("URL")));
                recipe.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                recipe.setPrepTime(cursor.getString(cursor.getColumnIndex("PrepTime")));
                recipe.setTotalTime(cursor.getString(cursor.getColumnIndex("TotalTime")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("Ingredients")));
                recipe.setInstructions(cursor.getString(cursor.getColumnIndex("Instructions")));
                recipe.setURL(cursor.getString(cursor.getColumnIndex("Picture")));

                result.add(recipe);
            } while (cursor.moveToNext());
        }
        return result;
    }


}
