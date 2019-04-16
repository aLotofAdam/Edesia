package com.example.edesia.presentation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
/*TODO why comment out?
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    //TODO private static final String DB_NAME = "recipes.db";
    private static final String DB_NAME = "recipe_db.db";
    private static final int DB_VERSION = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public List<Recipe> getRecipes() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions"};
        String table_name = "output";
        qb.setTables(table_name);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setURL(cursor.getString(cursor.getColumnIndex("URL")));
                recipe.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                recipe.setPrepTime(cursor.getString(cursor.getColumnIndex("PrepTime")));
                recipe.setTotalTime(cursor.getString(cursor.getColumnIndex("TotalTime")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("Ingredients")));
                recipe.setInstructions(cursor.getString(cursor.getColumnIndex("Instructions")));

                result.add(recipe);
            } while (cursor.moveToNext());
        }
        return result;
    }
    public List<String> getTitles(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Title"};
        String table_name = "output";
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


    public List<String> getURL(String URL) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"URL"};
        String table_name = "output";
        qb.setTables(table_name);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
           do {
                result.add(cursor.getString(cursor.getColumnIndex("URL")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Recipe> getRecipebyName(String Title) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions"};
        String table_name = "output";
        qb.setTables(table_name);
        //will query like select from where %patterm%
        Cursor cursor = qb.query(db, sqlSelect, "Title LIKE ?", new String[]{"%" + Title + "%"}, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setURL(cursor.getString(cursor.getColumnIndex("URL")));
                recipe.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
                recipe.setPrepTime(cursor.getString(cursor.getColumnIndex("PrepTime")));
                recipe.setTotalTime(cursor.getString(cursor.getColumnIndex("TotalTime")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("Ingredients")));
                recipe.setInstructions(cursor.getString(cursor.getColumnIndex("Instructions")));
                result.add(recipe);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //function to get all ingredient names
    public List<String> getIngredients()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        List<String>result = new ArrayList<>();

        String[] sqlSelect = {"Ingredients"};
        String tableName = "recipes";  //table name

        qb.setTables( tableName );
        Cursor cursor = qb.query(db, sqlSelect,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do{
                result.add( cursor.getString( cursor.getColumnIndex( "Ingredients" ) ));
            }while (cursor.moveToNext());
        }
        return result;
    }
}*/