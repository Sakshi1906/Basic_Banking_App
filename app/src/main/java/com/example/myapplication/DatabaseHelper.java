package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(6294719028,'Sakshi Kumari',75000.00,'sakshi@gmail.com','XXXXXXXXXXXX9876','ABC012345')");
        db.execSQL("insert into user_table values(6438362546,'Vikash Singh',75000.00,'vikash@gmail.com','XXXXXXXXXXXX9955','ABC032165')");
        db.execSQL("insert into user_table values(6976618478,'Ayushka Samdwar',62000.00,'ayushka@gmail.com','XXXXXXXXXXXX7635','ABC019843')");
        db.execSQL("insert into user_table values(7178563762,'Heenal Keshwani',62000.00,'heenal@gmail.com','XXXXXXXXXXXX6543','ABC075643')");
        db.execSQL("insert into user_table values(7387465267,'Umang Bansal',55000.00,'umang@gmail.com','XXXXXXXXXXXX5678','ABC012435')");
        db.execSQL("insert into user_table values(7565415627,'Alisha Bhardwaj',55000.00,'alisha@gmail.com','XXXXXXXXXXXX8476','ABC018734')");
        db.execSQL("insert into user_table values(7965431456,'Anshu Rohilla',6000.00,'anshu@gmail.com','XXXXXXXXXXXX2645','ABC046372')");
        db.execSQL("insert into user_table values(8176355673,'Saumya Srivastava',6000.00,'saumya@gmail.com','XXXXXXXXXXXX3647','ABC013245')");
        db.execSQL("insert into user_table values(8624256273,'Abhishek Kumar',9000.00,'abhishek@gmail.com','XXXXXXXXXXXX8564','ABC018453')");
        db.execSQL("insert into user_table values(9183648595,'Nayan Choudhary',9000.00,'nayan@gmail.com','XXXXXXXXXXXX8564','ABC037485')");
        db.execSQL("insert into user_table values(9363452677,'Avinash Mishra',4000.00,'avinash@gmail.com','XXXXXXXXXXXX4872','ABC012846')");
        db.execSQL("insert into user_table values(9846573627,'Payal Gupta',4000.00,'payal@gmail.com','XXXXXXXXXXXX4657','ABC017485')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
