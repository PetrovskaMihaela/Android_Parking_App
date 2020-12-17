package com.example.easypark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/*public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Login";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct DataBase_OpenHelper");
    }



    //private static final String USERS_TABLE = "user_entries";
    //private static final String CITIES_TABLE = "city_entries";
    //private static final String PARKINGS_TABLE = "user_entries";
    //private static final String REGS_TABLE = "user_entries";



    // Table 1
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    private static final String[] COLUMNS_1 = {EMAIL, PASSWORD};
    // Table 2
    public static final String C_NAME = "cityname";
    public static final String C_NUM = "citynum";
    private static final String[] COLUMNS_2 = {C_NAME, C_NUM};





    private static final String USERS_TABLE_CREATE = "CREATE TABLE " + USERS_TABLE + " (" + EMAIL + " INTEGER PRIMARY KEY, " + PASSWORD + " TEXT );";
    private static final String CITIES_TABLE_CREATE = "CREATE TABLE " + CITIES_TABLE + " (" + C_NAME + " INTEGER PRIMARY KEY, " + C_NUM + " INTEGER );";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);
        db.execSQL(CITIES_TABLE_CREATE);
        //db.execSQL("Create table city(name text primary key, numPark text, listPark text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// SAVE USER DATA FIRST!!!
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CITIES_TABLE);
        onCreate(db);
    }


    public boolean insert_1(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("EMAIL", email);
        contentValues1.put("PASSWORD", password);
        long ins = db.insert("USERS_TABLE", null, contentValues1);
        if (ins == -1)
            return false;
        else return true;
    }
    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from USERS_TABLE where EMAIL=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from USERS_TABLE where EMAIL=? and PASSWORD=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public boolean insert_2(String cityname, String citynumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("C_NAME", cityname);
        contentValues2.put("C_NUM", citynumber);
        long ins = db.insert("CITIES_TABLE", null, contentValues2);
        if (ins == -1)
            return false;
        else return true;
    }*/



public class DatabaseHelper extends SQLiteOpenHelper {

    private Array cityList;
    private String email;

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("Create table city(cityname text primary key, citynumber integer )");
        ContentValues cv = new ContentValues();
        cv.put("cityname","Скопје");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Куманово");
        cv.put("citynumber", 3);
        db.insert("city", null, cv );
        cv.put("cityname","Велес");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Штип");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Гевгелија");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Струмица");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Прилеп");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Битола");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Охрид");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Гостивар");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );
        cv.put("cityname","Тетово");
        cv.put("citynumber", 5);
        db.insert("city", null, cv );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists city");

    }

    public boolean insert_1(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public boolean insert_2(String name, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cityname", name);
        contentValues.put("citynumber", number);
        long ins = db.insert("city", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


}
