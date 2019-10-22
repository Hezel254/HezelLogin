package com.example.hezellogin.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hezellogin.model.Details

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "details", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DETAILS_TABLE = ("CREATE TABLE " +
                 "login(id INTEGER PRIMARY KEY, email TEXT, password TEXT )")
        val create_task_table=("create table task(id integer auto increment,name text,description text,date text)")
        if (db != null) {
            db.execSQL(CREATE_DETAILS_TABLE)
            db.execSQL(create_task_table)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS login")
            db.execSQL("drop table if exists task")
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

    fun inserttask(){
        val tasks=ContentValues()
        tasks.put("name","")
        tasks.put("description","")
        tasks.put("date","")
        val db=this.writableDatabase
        db.insert("task",null,tasks)
        db.close()
    }

    fun edittask() {
        val tasks = ContentValues()
        tasks.put("name", "")
        tasks.put("description", "")
        tasks.put("id", "")
        val db = this.writableDatabase
        db.update("task", null, arrayOf(""))
        db.close()
    }

    fun deletetask() {
        val tasks = ContentValues()
        tasks.put("id", "")
        val db = this.writableDatabase
        db.delete("task", "id", arrayOf(""))
        db.close()
    }



    fun readData(details:Details):Cursor{
        val db=this.readableDatabase
      return db.rawQuery("SELECT * FROM login WHERE email=? AND password=?", arrayOf(details.email,details.password))
    }

}
