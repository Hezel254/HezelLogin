package com.example.hezellogin.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hezellogin.model.Details

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "details", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DETAILS_TABLE = ("CREATE TABLE " +
                 "login(id INTEGER PRIMARY KEY, email TEXT, password TEXT )")
        if (db != null) {
            db.execSQL(CREATE_DETAILS_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS login")
        }
        onCreate(db)
    }

    fun insertData(details:Details){
        val values = ContentValues()
        values.put("email", details.email)
        values.put("password", details.password)
        val db = this.writableDatabase
        db.insert("login", null, values)
        db.close()
    }


}