package com.example.abc.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 10-08-2017.
 */

public class DATABASEHANDLER extends SQLiteOpenHelper {

    private static final String TABLE_REGISTRATION = "reg2";
    private static final String KEY_ID = "id";
    private static final String KEY_phone = "phone";


    public DATABASEHANDLER(Context context) {
        super(context, dbversion.DATABASE_NAME,null,dbversion.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTRATION = "CREATE TABLE IF NOT EXISTS "+TABLE_REGISTRATION+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_phone+" TEXT not null unique"+")";
        db.execSQL(CREATE_REGISTRATION);
        Log.e("sqffl" , CREATE_REGISTRATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        onCreate(db);
    }
    public void addDATA(Registration reeg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_phone , reeg.getphone());
        db.insert(TABLE_REGISTRATION,null,value);
        db.close();

    }

    public List<Registration> getAllData(){
        List<Registration> list = new ArrayList<Registration>();
        String select = "SELECT * FROM " + TABLE_REGISTRATION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do{
                Registration obj = new Registration();
                obj.setId(Integer.parseInt(cursor.getString(0)));
                obj.setphone(cursor.getString(1));
                list.add(obj);
            }while (cursor.moveToNext());
        }
        return list;

    }

}
