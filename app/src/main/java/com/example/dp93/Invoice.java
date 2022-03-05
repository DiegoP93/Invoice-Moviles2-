package com.example.dp93;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Invoice extends SQLiteOpenHelper {

    String tblcustomer = "CREATE TABLE customer(idcust text primary key,name text,email text,phone text)";
    String tblinvoice = "CREATE TABLE invoice(idinvoice integer primary key autoincrement,idcust text,coste integer,balance integer,obs text)";
    String tblpayment = "CREATE TABLE payment(idpayment integer primary key autoincrement,idinvoice integer,date text,price integer)";
    public Invoice(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblcustomer);
        db.execSQL(tblinvoice);
        db.execSQL(tblpayment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE customer");
        db.execSQL(tblcustomer);
        db.execSQL("DROP TABLE invoice");
        db.execSQL(tblinvoice);
        db.execSQL("DROP TABLE payment");
        db.execSQL(tblpayment);

    }
}
