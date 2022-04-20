package com.example.higherorlower;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    ArrayList<Country> countries = new ArrayList<>();

    public DBHelper(@Nullable Context context) {
        super(context, "higherLower_DB", null, 2);
    }


    //When database is created the 2 tables for the game are generated
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE country (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, pop INTEGER, size INTEGER)";
        sqLiteDatabase.execSQL(query1);

        countries.add(new Country("Afghanistan",29835392, 250000));
        countries.add(new Country("Argentina",41769726, 1068296));
        countries.add(new Country("Australia",21766711, 2967893));
        countries.add(new Country("Belgium",10431477, 11787));
        countries.add(new Country("Bolivia",10118683, 424162));
        countries.add(new Country("Brazil",203429773, 3286470));
        countries.add(new Country("Cambodia",14701717, 69900));
        countries.add(new Country("Canada",34030589, 3855081));
        countries.add(new Country("Chile",16888760, 292258));
        countries.add(new Country("China",1336718015, 3705386));
        countries.add(new Country("Colombia",44725543, 439733));
        countries.add(new Country("Croatia",4483804, 21831));
        countries.add(new Country("Dominican Republic",9956648, 18815));
        countries.add(new Country("Egypt",82079636, 386660));
        countries.add(new Country("Ethiopia",90873739, 435184));
        countries.add(new Country("France",65312249, 211208));
        countries.add(new Country("Germany",81471834, 137846));
        countries.add(new Country("Greece",10760136, 50942));
        countries.add(new Country("Haiti",9719932, 10714));
        countries.add(new Country("India",1189172906, 1269338));
        countries.add(new Country("Iran",77891220, 636293));
        countries.add(new Country("Italy",61016804, 116305));


        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Country country : countries) {
                values.put("name", country.getCountryName());
                values.put("pop", country.getCountryPop());
                values.put("size", country.getCountrySize());
                db.insert("country", null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query1 = "DROP TABLE IF EXISTS country";
        sqLiteDatabase.execSQL(query1);
        onCreate(sqLiteDatabase);
    }

    //Function to select random record in country table
    public Country selectRandomCountry(){
        Country randomCountry = new Country("countName", 0, 0);;
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "country");

        Integer recordId = (int) (Math.random() * (count - 1)) + 1;
        String query = "Select name, pop, size FROM country WHERE id = " + recordId;

        Cursor c =  db.rawQuery(query, null);
        if (c.moveToFirst()){
            do {
                // Passing values
                String countName = c.getString(0);
                Integer countPop = c.getInt(1);
                Integer countSize = c.getInt(2);

                randomCountry = new Country(countName, countPop, countSize);
            } while(c.moveToNext());
        }
        c.close();
        db.close();

        return randomCountry;

    }



}
