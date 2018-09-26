package com.example.android.registration.Databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

import com.example.android.registration.WorkshopItem;

import java.util.ArrayList;

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

    SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_CREATE = "create table workshopDetails (id integer primary key autoincrement , " +
            "title text not null, organiser text not null, description1 text not null, description2 text not null);";

    public WorkshopDatabaseHelper(FragmentActivity context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;

        this.sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_TITLE, "Android Workshop");
        values.put(COLUMN_ORGANISED_BY, "ACFAI");
        values.put(COLUMN_DESCRIPTION1, "2 days workshop on Android");
        values.put(COLUMN_DESCRIPTION2, "This 2 days National Workshop is organised by ACFAI Tech School, The ACFAI University, Haipur, in association with BDAC-ACTS, Jaipur. The course included a brief Introduction to Android, Project Development and Market and Job Opportunities.");

        this.sqLiteDatabase.insert(TABLE_NAME, null, values);
        this.sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(sqLiteDatabase);

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

            else {
                return null ;
            }
        }catch (Exception e) {
            e.printStackTrace() ;
        }

        return list ;
    }
}
