package com.example.easypark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/*public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = DatabaseHelper.class.getSimpleName();


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EasyPark.db";
    private static final String USER_TABLE= "user_entries";

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private static final String[] COLUMNS1 = {EMAIL, PASSWORD};

    private static final String USER_TABLE_CREATE =
            "CREATE TABLE USER_TABLE (EMAIL TEXT PRIMARY KEY, PASSWORD TEXT );";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct DatabaseHelper");
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    public boolean insert_1(String email, String password){
        SQLiteDatabase db = mWritableDB;
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);
        long ins = db.insert("USER_TABLE", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }
    public Boolean checkemail(String email){
        SQLiteDatabase db = mReadableDB;
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE EMAIL=?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = mReadableDB;
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE EMAIL=? AND PASSWORD=?", new String[]{email, password});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE");
        onCreate(db);

    }
}
*/




public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("Create table city(cityname text primary key, citynumber int)");
        db.execSQL("Create table park(parkName text primary key, cityNamee text,  parkSpaces int, takenSpaces int)");
        fillCityWithData(db);
        fillParkWithData(db);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists city");
        db.execSQL("drop table if exists park");

    }

    public void fillCityWithData(SQLiteDatabase db) {

        String[] cities = {"Скопје", "Куманово", "Велес", "Штип", "Гевгелија", "Струмица", "Прилеп", "Битола", "Охрид", "Гостивар", "Тетово"};
        int[] numbers = {7, 5, 3, 3, 4, 3, 2, 2, 3, 2, 2};
        // Create a container for the data.
        ContentValues values = new ContentValues();

        for (int i=0; i < cities.length;i++) {
            // Put column/value pairs into the container, overriding existing values.
            values.put("cityname", cities[i]);
            values.put("citynumber", numbers[i]);
            db.insert("city", null, values);
        }
    }

   private void fillParkWithData(SQLiteDatabase db) {
        String[] parkingLots = {"Скопје1", "Скопје2", "Скопје3", "Скопје4", "Скопје5", "Скопје6", "Скопје7",
                "Куманово1", "Куманово2", "Куманово3", "Куманово4", "Куманово5",
                "Велес1", "Велес2", "Велес3",
                "Штип1", "Штип2", "Штип3",
                "Гевгелија1", "Гевгелија2", "Гевгелија3", "Гевгелија4",
                "Струмица1", "Струмица2", "Струмица3",
                "Прилеп1", "Прилеп2",
                "Битола1","Битола2",
                "Охрид1","Охрид2","Охрид3",
                "Гостивар1","Гостивар2",
                "Тетово1","Тетово2"};

        String[] cityP= {"Скопје", "Скопје", "Скопје", "Скопје", "Скопје", "Скопје", "Скопје",
                "Куманово", "Куманово", "Куманово", "Куманово", "Куманово",
                "Велес", "Велес", "Велес",
                "Штип", "Штип", "Штип",
                "Гевгелија", "Гевгелија", "Гевгелија", "Гевгелија",
                "Струмица", "Струмица", "Струмица",
                "Прилеп", "Прилеп",
                "Битола","Битола",
                "Охрид","Охрид","Охрид",
                "Гостивар","Гостивар",
                "Тетово","Тетово"};

        int[] spaces = {30, 30, 30, 30, 30, 30, 30, 20, 20, 20, 20, 20, 25, 25, 25, 33, 33, 33, 25, 25, 25, 25, 30, 30, 30, 34, 34, 25, 25, 33, 33, 33, 20, 20, 20, 20};
        int zafateni = 0;
        int free = 30;
        ContentValues values = new ContentValues();

        for (int i=0; i < parkingLots.length;i++) {
            // Put column/value pairs into the container, overriding existing values.
            values.put("parkName", parkingLots[i]);
            values.put("cityNamee", cityP[i]);
            values.put("parkSpaces", free);
            values.put("takenSpaces", zafateni);
            db.insert("park", null, values);
        }
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


  public City query(int position) {
      String query = "SELECT * FROM city ORDER BY cityname ASC " +
              "LIMIT " + position + ",1";

      Cursor cursor = null;
      City entry = new City();

      SQLiteDatabase db = this.getReadableDatabase();
      cursor = db.rawQuery(query, null);
      cursor.moveToFirst();
      entry.setCityName(cursor.getString(cursor.getColumnIndex("cityname")));
      entry.setCityNumber(cursor.getColumnIndex("citynumber"));
      cursor.close();
      return entry;

  }


    public Parking querypark(int position) {
        String query = "SELECT * FROM park ORDER BY parkName ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        Parking entry = new Parking();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        entry.setParkName(cursor.getString(cursor.getColumnIndex("parkName")));
        entry.setParkCity(cursor.getString(cursor.getColumnIndex("cityNamee")));
        entry.setParkSpaces(cursor.getColumnIndex("parkSpaces"));
        entry.setTakenSpaces(cursor.getColumnIndex("takenSpaces"));
        cursor.close();
        return entry;

    }



    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "city");
     }

     public long countpark(){
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "park");
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