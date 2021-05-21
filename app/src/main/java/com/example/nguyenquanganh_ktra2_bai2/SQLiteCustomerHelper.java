package com.example.nguyenquanganh_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nguyenquanganh_ktra2_bai2.model.Customer;

import java.util.ArrayList;

public class SQLiteCustomerHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VeMayBay.DB";
    public static final int DATABASE_VERSION = 1;
    public SQLiteCustomerHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE datve (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "departure TEXT," +
                "date TEXT," +
                "luggage BOOLEAN)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("departure", customer.getDeparture());
        values.put("date", customer.getDate());
        values.put("luggage", customer.isLuggage());

        SQLiteDatabase database = getWritableDatabase();
        return database.insert("datve", null, values);
    }

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("datve", null, null, null,
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String departure = cursor.getString(2);
                String date = cursor.getString(3);
                boolean luggage = cursor.getInt(4) == 1;
                list.add(new Customer(id, name, departure, date, luggage));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int updateCustomer(Customer customer) {
        ContentValues values = new ContentValues();
        values.put("name", customer.getName());
        values.put("departure", customer.getDeparture());
        values.put("date", customer.getDate());
        values.put("luggage", customer.isLuggage());
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(customer.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("datve",
                values, whereClause, whereArgs);
    }

    public int deleteCustomer(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("datve",
                whereClause, whereArgs);
    }

    public ArrayList<Customer> getCustomerByName(String name) {
        ArrayList<Customer> list = new ArrayList<>();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%" + name + "%"};
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("datve", null, whereClause,
                whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String customerName = cursor.getString(1);
            String customerDeparture = cursor.getString(2);
            String customerDate = cursor.getString(3);
            boolean hasLuggage = cursor.getInt(4) == 1;
            list.add(new Customer(id, customerName, customerDeparture, customerDate, hasLuggage));
        }
        return list;
    }
}
