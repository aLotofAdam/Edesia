package com.example.edesia.presentation;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.edesia.presentation.RecipeModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper
{
    private static final String DB_NAME = "recipes.db";
    private static final int DB_VERSION = 1;

    public Database(Context context)
    {
        super( context, DB_NAME, null, DB_VERSION );
    }

    // function to get all food data
    public List<RecipeModel> getRecipeModel()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //all column names
        String[] sqlSelect = {"ID","URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions","Picture"};
        String tableName = "recipes";

        qb.setTables( tableName );
        Cursor cursor = qb.query(db, sqlSelect,null,null,null,null,null);
        List<RecipeModel>result = new ArrayList<>(  );
        if (cursor.moveToFirst())
        {
            do {
                RecipeModel recipeModel = new RecipeModel(  );
                recipeModel.setID( cursor.getInt( cursor.getColumnIndex( "ID" ) ) );
                recipeModel.setTitle( cursor.getString( cursor.getColumnIndex( "Title" ) ) );
                recipeModel.setPrepTime( cursor.getString( cursor.getColumnIndex( "PrepTime" ) ) );
                recipeModel.setTotalTime( cursor.getString( cursor.getColumnIndex( "TotalTime" ) ) );
                recipeModel.setIngredients( cursor.getString( cursor.getColumnIndex( "Ingredients" ) ) );
                recipeModel.setInstructions( cursor.getString( cursor.getColumnIndex( "Instructions" ) ) );
                recipeModel.setPicture( cursor.getString( cursor.getColumnIndex( "Picture" ) ) );
                result.add( recipeModel  );
            }while (cursor.moveToNext());

        }
        return result;
    }


    //fucntion to get all food name
    public List<String> getTitle()
    {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //all column names
        String[] sqlSelect = {"Title"};
        String tableName = "recipes";  //table name

        qb.setTables( tableName );
        Cursor cursor = qb.query(db, sqlSelect,null,null,null,null,null);
        List<String>result = new ArrayList<>(  );
        if (cursor.moveToFirst())
        {
            do{
                result.add( cursor.getString( cursor.getColumnIndex( "Title" ) ));
            }while (cursor.moveToNext());

        }
        return result;
    }

    //function to get food by food name
    public List<RecipeModel> getRecipeByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //all column names
        String[] sqlSelect = {"ID","URL", "Title", "PrepTime", "TotalTime", "Ingredients", "Instructions","Picture"};
        String tableName = "recipes";  //table name

        qb.setTables( tableName );

        //this will be like a query, Select * from foodmodel where Name LIKE %example%
        //exact name,remove the % sign and + sign,
        //Cursor cursor = qb.query(db, sqlSelect,"Food Name LIKE ?", new String[]{name}, null,null,null);

        //Cursor cursor = qb.query(db, sqlSelect," food_name LIKE ?", new String[]{"%" + name + "%"}, null,null,null);
        Cursor cursor = qb.query( db,sqlSelect,"Title LIKE ?",new String[]{"%"+name+"%"},null,null,null );
        List<RecipeModel>result = new ArrayList<>(  );
        if (cursor.moveToFirst())
        {
            do {
                RecipeModel recipeModel = new RecipeModel(  );
                recipeModel.setID( cursor.getInt( cursor.getColumnIndex( "ID" ) ) );
                recipeModel.setTitle( cursor.getString( cursor.getColumnIndex( "Title" ) ) );
                recipeModel.setPrepTime( cursor.getString( cursor.getColumnIndex( "PrepTime" ) ) );
                recipeModel.setTotalTime( cursor.getString( cursor.getColumnIndex( "TotalTime" ) ) );
                recipeModel.setIngredients( cursor.getString( cursor.getColumnIndex( "Ingredients" ) ) );
                recipeModel.setInstructions( cursor.getString( cursor.getColumnIndex( "Instructions" ) ) );
                recipeModel.setPicture( cursor.getString( cursor.getColumnIndex( "Picture" ) ) );
                result.add( recipeModel  );
            }while (cursor.moveToNext());

        }
        return result;
    }
}

