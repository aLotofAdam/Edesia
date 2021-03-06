package com.example.edesia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper {

    dbHelper DbHelper;

    public DatabaseHelper(Context context) {

        DbHelper = new dbHelper(context);
    }

    static class dbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "user.db";
        private static final String TABLE_NAME = "UserTable";
        private static final int DATABASE_Version = 1;
        private static final String USER_ID = "ID";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                                    " ("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    ""+NAME+" VARCHAR(255) ,"+ PASSWORD +" VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;


        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
            }
        }
    }
    public long insertData(String name, String pass) {

        SQLiteDatabase dbb = DbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, name);
        contentValues.put(dbHelper.PASSWORD, pass);
        long id = dbb.insert(dbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData() {

        SQLiteDatabase db = DbHelper.getWritableDatabase();
        String[] columns = {dbHelper.USER_ID, dbHelper.NAME, dbHelper.PASSWORD};
        Cursor cursor = db.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid = cursor.getInt(cursor.getColumnIndex(dbHelper.USER_ID));
            String name =cursor.getString(cursor.getColumnIndex(dbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(dbHelper.PASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public int updateName(String old , String newName) {

        SQLiteDatabase db = DbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME,newName);
        String[] whereArgs= {old};
        int count =db.update(dbHelper.TABLE_NAME,contentValues, dbHelper.NAME+" = ?",whereArgs );
        return count;
    }

    public  int delete(String User) {

        SQLiteDatabase db = DbHelper.getWritableDatabase();
        String[] find ={User};

        int count =db.delete(dbHelper.TABLE_NAME , dbHelper.NAME+" = ?",find);
        return  count;
    }
}