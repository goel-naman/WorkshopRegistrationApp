package com.example.android.registration.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.android.registration.WorkshopItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WorkshopDatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workshopDetails.db";
    private static final String TABLE_NAME = "workshopDetails";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ORGANISED_BY = "organiser";
    private static final String COLUMN_DESCRIPTION1 = "description1";
    private static final String COLUMN_DESCRIPTION2 = "description2";

    private SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_CREATE = "create table workshopDetails (id integer primary key autoincrement , " +
            "title text not null, organiser text not null, description1 text not null, description2 text not null);";

    public WorkshopDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        createDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;
    }

    private void insert() {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, "Android Workshop");
        values.put(COLUMN_ORGANISED_BY, "ACFAI");
        values.put(COLUMN_DESCRIPTION1, "2 days workshop on Android");
        values.put(COLUMN_DESCRIPTION2, "This 2 days National Workshop is organised by ACFAI Tech School, The ACFAI University, Haipur, in association with BDAC-ACTS, Jaipur. The course included a brief Introduction to Android, Project Development and Market and Job Opportunities.");

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(sqLiteDatabase);

    }

    private void createDatabase(){

        boolean dbExist = checkDataBase();

        if (dbExist) {
            Log.v("DB Exists", "db exists");
        }

        boolean dbExist1 = checkDataBase();
        if (!dbExist1) {
            this.getReadableDatabase();
            this.close();
            insert();
        }

    }
    public ArrayList<WorkshopItem> getDemoList(){
        ArrayList<WorkshopItem> list = new ArrayList<>();
        try {
            sqLiteDatabase = this.getReadableDatabase() ;

            String query_params = "SELECT * FROM " + TABLE_NAME ;
            Cursor cSor = sqLiteDatabase.rawQuery(query_params, null) ;

            if (cSor.moveToFirst())
            {
                do {
                    String _title = cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_TITLE)) ;
                    String _description1 = cSor.getString(cSor.getColumnIndexOrThrow(COLUMN_DESCRIPTION1)) ;
                    list.add(new WorkshopItem(_title, _description1)) ;
                }while (cSor.moveToNext()) ;
            }
            cSor.close();
        }catch (Exception e) {
            e.printStackTrace() ;
        }
        return list;
    }
    //Check database already exist or not
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            String myPath = "data/data/com.example.android.registration/databases/" + DATABASE_NAME;
            File dbFile = new File(myPath);
            checkDB = dbFile.exists();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return checkDB;
    }
}
