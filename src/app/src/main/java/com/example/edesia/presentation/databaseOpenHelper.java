package com.example.edesia.presentation;

import android.content.Context;

public class databaseOpenHelper extend SQLiteAssetHelper {
    private static final String DATABASE_NAME="recip_db.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseHelper(Context C){
        super(c, DATABASE_NAME, null, DATABASE_VERSION );

    }

}
