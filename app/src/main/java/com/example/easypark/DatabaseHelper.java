package com.example.easypark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "epark.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("Create table city(cityname text primary key, citynumber int)");
        db.execSQL("Create table park(parkName text primary key, cityNamee text,  parkSpaces int, takenSpaces int, lat double, long double)");

        db.execSQL("Create table reservation(rowid int primary key, userR text, cityR text, parkR text, dateR text, timeR text )");

        fillCityWithData(db);
        fillParkWithData(db);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists city");
        db.execSQL("drop table if exists park");
        db.execSQL("drop table if exists reservation");
        onCreate(db);

    }

    public boolean insertReservation(String user, String city, String park, String date, String time){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userR", user);
        contentValues.put("cityR", city);
        contentValues.put("parkR", park);
        contentValues.put("dateR", date);
        contentValues.put("timeR", time);
        long ins = db.insert("reservation", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public void fillCityWithData(SQLiteDatabase db) {

        String[] cities = {"Скопје", "Куманово", "Велес", "Штип", "Гевгелија", "Струмица", "Прилеп", "Битола", "Охрид", "Гостивар", "Тетово"};
        int[] numbers = {5, 2, 2, 2, 1, 1, 2, 2, 2, 1, 1};

        ContentValues values = new ContentValues();

        for (int i=0; i < cities.length;i++) {
            // Put column/value pairs into the container, overriding existing values.
            values.put("cityname", cities[i]);
            values.put("citynumber", numbers[i]);
            db.insert("city", null, values);
        }
    }

   private void fillParkWithData(SQLiteDatabase db) {
        String[] parkingLots = {"Паркинг City Mall", "Паркинг ГТЦ", "Паркинг Нова Македонија", "Катна Гаража Илинден", "Катна Гаража Кресненско Востание",
                "Соколана", "Паркинг Kit Go",
                "Паркинг Хемиска Гимнаија", "Паркинг Пазариште",
                "Паркинг Дом на Култура", "Паркинг Автобуска Станица",
                "Паркинг Железничка",
                "Глобал Паркинг",
                "Паркинг Гаража", "Паркинг Автобуска Станица",
                "Паркинг Стара Болница","Паркинг Широк Сокак",
                "ЈП Билјанини Извори","Паркинг Пристаниште",
                "Паркинг Зона",
                "Паркинг Тетово",};

        String[] cityP= {"Скопје", "Скопје", "Скопје", "Скопје", "Скопје",
                "Куманово", "Куманово",
                "Велес", "Велес",
                "Штип", "Штип",
                "Гевгелија",
                "Струмица",
                "Прилеп", "Прилеп",
                "Битола","Битола",
                "Охрид","Охрид",
                "Гостивар",
                "Тетово"};

        double[] latitude = {42.005864, 41.994514, 41.993442, 41.985663 , 41.996776,
                42.128971, 42.135614,
                41.718129, 41.713903,
                41.737259, 41.741124,
                41.143215,
                41.439633,
                41.343759, 41.344044,
                41.026927, 41.030836,
                41.117588, 41.112351,
                41.796165,
                42.007646
        };
        double[] longitude = {21.392932, 21.437454, 21.422464, 21.465201, 21.437004,
                21.718501, 21.728963,
                21.772912, 21.785763,
                22.190368, 22.189238,
                22.512149,
                22.639943,
                21.551713, 21.540177,
                21.333055, 21.334509,
                20.798746, 20.799422,
                20.908620,
                20.968645
        };

        int[] free = {400, 250, 200, 180, 421,
                150, 180,
                150, 200,
                125, 100,
                120,
                200,
                250, 100,
                165, 155,
                250, 265,
                220,
                100};
        int taken []= {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ContentValues values = new ContentValues();

        for (int i=0; i < parkingLots.length;i++) {

            values.put("parkName", parkingLots[i]);
            values.put("cityNamee", cityP[i]);
            values.put("parkSpaces", free[i]);
            values.put("takenSpaces", taken[i]);
            values.put("lat", latitude[i]);
            values.put("long",longitude[i] );
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
      entry.setCityNumber(cursor.getInt(cursor.getColumnIndex("citynumber")));
      cursor.close();
      return entry;

  }



    public Parking querypark(int position) {
        //String query = "SELECT parkName, parkSpaces, takenSpaces FROM park WHERE cityNamee = ? ORDER BY parkName ASC LIMIT "+ position + ",1";

        Cursor cursor;
        Parking entry = new Parking();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery( "Select * from park order by parkName asc limit " + position + ",1", null);
        cursor.moveToFirst();

        entry.setParkName(cursor.getString(cursor.getColumnIndex("parkName")));
        entry.setParkCity(cursor.getString(cursor.getColumnIndex("cityNamee")));
        entry.setParkSpaces(cursor.getInt(cursor.getColumnIndex("parkSpaces")));
        entry.setTakenSpaces(cursor.getInt(cursor.getColumnIndex("takenSpaces")));
        //entry.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
        //entry.setLng(cursor.getDouble(cursor.getColumnIndex("lng")));

        cursor.close();

            return entry;

    }

    public Reservation queryreservation(int position, String user){
        Cursor cursor;
        Reservation entry = new Reservation();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery( "Select * from reservation where userR=? limit " + position + ",1", new String[]{user});
        cursor.moveToFirst();

        entry.setUserR(cursor.getString(cursor.getColumnIndex("userR")));
        entry.setCityR(cursor.getString(cursor.getColumnIndex("cityR")));
        entry.setParkR(cursor.getString(cursor.getColumnIndex("parkR")));
        entry.setDateR(cursor.getString(cursor.getColumnIndex("dateR")));
        entry.setTimeR(cursor.getString(cursor.getColumnIndex("timeR")));

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

    public long countreservation() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "reservation");
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

    public int numberResPerUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from reservation where userR=?", new String[]{user});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getCount();
            cursor.close();
            return count;
        } else return 0;
    }



    public int numberResAtDateTime(String date, String time, String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from reservation where dateR=? and timeR=? and parkR=?", new String[]{date, time, park});
        int number = 0;
        if(cursor.moveToFirst()){
            number = cursor.getCount();
            cursor.close();
            return number;
        }else return 0;
    }

    public double latitude (String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(4);
        }else return 0;
    }

    public double longitude (String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(5);
        }else return 0;
    }



}