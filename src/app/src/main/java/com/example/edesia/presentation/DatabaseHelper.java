package com.example.edesia.presentation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "User.db";
        private static final String TABLE_NAME = "UserTable";
        private static final int DATABASE_Version = 1;
        private static final String USER_ID = "ID";
        private static final String USER_NAME = "Username";
        private static final String NAME = "Name";
        private static final String EMAIL = "Email";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +USER_NAME+" VARCHAR(255),"+EMAIL+" VARCHAR(255),"
                +NAME+" VARCHAR(255),"+ PASSWORD +" VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;



        public DatabaseHelper(Context context) {
            super(context, "User.db", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {

                db.execSQL("Create table UserTable(Username text primary key, Password text, Name text, Email text)");
                db.execSQL("Create table PlannedMeals(Username text, Month text, Day integer, Breakfast integer, Lunch integer, Dinner integer, CONSTRAINT fk_Username FOREIGN KEY (Username) REFERENCES UserTable(Username))");
                db.execSQL("Create table FavoriteRecipes(Username text, RecipeID integer, CONSTRAINT fk_Username FOREIGN KEY (Username) REFERENCES UserTable(Username))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("drop table if exists UserTable");
        }

    public boolean insertUserData(String name, String username, String email, String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", username);
        contentValues.put("Password", pass);
        contentValues.put("Name", name);
        contentValues.put("Email", email);

        long id = db.insert("UserTable", null , contentValues);
        if(id == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean insertPlannedMeal(String user, String month, int day, String mealTime, int RecipeID){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("Username", user);
            contentValues.put("Month", month);
            contentValues.put("Day", day);

            if(mealTime.equals("Breakfast")) {
                contentValues.put("Breakfast", RecipeID);
            }
            if(mealTime.equals("Lunch")) {
                contentValues.put("Lunch", RecipeID);
            }
            if(mealTime.equals("Dinner")) {
            contentValues.put("Dinner", RecipeID);

            }

            long id = db.insert("PlannedMeals", null, contentValues);
            if(id == -1){
                return false;
            }else{
                return true;
        }
    }

    public boolean insertFavoriteRecipe(String user, int RecipeID){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", user);
        contentValues.put("RecipeID", RecipeID);

        long id = db.insert("FavoriteRecipes", null, contentValues);
        if(id == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUsername(String userName){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from UserTable where Username=?", new String[]{userName});
            if(cursor.getCount() > 0){
                return false;
            }else{
                return true;
            }
    }

    public boolean checkLogin(String user, String pass){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from UserTable where Username=? and Password=?", new String[]{user,pass});

            if(cursor.getCount() > 0){
                return true;
            }else{
                return false;
            }
    }
/*
    public String getData() {

        SQLiteDatabase db = DbHelper.getWritableDatabase();
        String[] columns = {dbHelper.USER_ID, dbHelper.NAME, dbHelper.USER_NAME, dbHelper.EMAIL, dbHelper.PASSWORD};
        Cursor cursor = db.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int uid = cursor.getInt(cursor.getColumnIndex(dbHelper.USER_ID));
            String name =cursor.getString(cursor.getColumnIndex(dbHelper.NAME));
            String username =cursor.getString(cursor.getColumnIndex(dbHelper.USER_NAME));
            String email =cursor.getString(cursor.getColumnIndex(dbHelper.EMAIL));
            String  password =cursor.getString(cursor.getColumnIndex(dbHelper.PASSWORD));
            buffer.append(uid+ "   " + name + "   " + username + "   " + email + "   " + password +" \n");
        }
        return buffer.toString();
    }
*/
    public int updateUsername(String old , String newName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,newName);
        String[] findUser= {old};
        int count =db.update(TABLE_NAME,contentValues, USER_NAME+" = ?",findUser );
        return count;
    }

    public  int delete(String User) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] findUser ={User};

        int count =db.delete(TABLE_NAME , USER_NAME+" = ?",findUser);
        return  count;
    }
}