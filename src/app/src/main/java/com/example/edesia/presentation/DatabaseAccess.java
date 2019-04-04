package com.example.edesia.presentation;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }
    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);

        }
        return instance;
    }
    public void openDb(){
        this.database=openHelper.getWritableDatabase();
    }
    public void closeDb(){
        if(database != null){
            this.database.close();
        }
    }
    public String getAddress(String name){
        c = database.rawQuery("select Recipe from Table1 where Name = '"+recipename+"'", newString[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String address = c.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();
    }

}
