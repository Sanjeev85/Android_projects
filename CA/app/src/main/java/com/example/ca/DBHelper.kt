package com.example.ca

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val q = "CREATE TABLE EMP (id INTEGER PRIMARY KEY, salary text, name text, age text)"
        db.execSQL(q)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun addData(name: String, age: String, salary: String) {


        val values = ContentValues()

        values.put(NAME_COl, name)
        values.put(AGE_COL, age)
        values.put(SALARY_COL, salary)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun retrieve_data(): Cursor? {
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object {

        private val DATABASE_NAME = "NEW_DB"

        private val DATABASE_VERSION = 3

        val TABLE_NAME = "EMP"

        val ID_COL = "id"

        val NAME_COl = "name"

        val AGE_COL = "age"

        val SALARY_COL = "salary"
    }
}


