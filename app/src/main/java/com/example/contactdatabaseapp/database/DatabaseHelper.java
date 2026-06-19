package com.example.contactdatabaseapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.contactdatabaseapp.model.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =
                "CREATE TABLE contacts (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "phone TEXT NOT NULL," +
                        "email TEXT NOT NULL," +
                        "avatar INTEGER NOT NULL" +
                        ")";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean addContact(String name, String phone, String email, int avatar) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("email", email);
        values.put("avatar", avatar);

        long result = db.insert("contacts", null, values);

        return result != -1;
    }

    public ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }


}

