package com.danh.iot.com.danh.iot.database;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Danh on 5/24/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "iot.db";
    public static final String TEMPERATURE_TABLE_NAME = "temperature";
    public static final String TEMPERATURE_COLUMN_ID = "id";
    public static final String TEMPERATURE_COLUMN_CUSTOMER = "customer";
    public static final String TEMPERATURE_COLUMN_SENSOR = "sensor";
    public static final String TEMPERATURE_COLUMN_LOCATION = "location";
    public static final String TEMPERATURE_COLUMN_DATETIME = "datetime";
    public static final String TEMPERATURE_COLUMN_VALUE = "value";
    public static final String TEMPERATURE_COLUMN_IP = "ip";
    private HashMap hp;

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
            "create table temperature " +
                    "(id integer primary key, customer text,sensor text,location text, datetime text,value text, ip text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS temperature");
        onCreate(db);
    }

    public boolean insertContact  (String customer, String sensor, String location, String datetime,float value, String ip)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer", customer);
        contentValues.put("sensor", sensor);
        contentValues.put("location", location);
        contentValues.put("datetime", datetime);
        contentValues.put("value", value);
        contentValues.put("ip", ip);
        db.insert("temperature", null, contentValues);
        return true;
    }

    public Cursor getData(String customer){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from temperature where id="+customer+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TEMPERATURE_TABLE_NAME);
        return numRows;
    }

    public boolean updateTemperature (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("temperature", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteTemperature (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("temperature",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

//    public ArrayList<String> getAllTemperature()
//    {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from temperature", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
