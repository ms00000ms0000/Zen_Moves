package com.example.zenmoves

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "zenmoves.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE activities(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                date TEXT,
                exerciseType TEXT,
                duration INTEGER,
                calories INTEGER,
                steps INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS activities")
        onCreate(db)
    }

    fun insertData(model: ActivityModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("date", model.date)
            put("exerciseType", model.exerciseType)
            put("duration", model.duration)
            put("calories", model.calories)
            put("steps", model.steps)
        }
        return db.insert("activities", null, values) != -1L
    }

    fun getAllData(): List<ActivityModel> {
        val db = readableDatabase
        val list = mutableListOf<ActivityModel>()
        val cursor = db.rawQuery("SELECT * FROM activities", null)
        while (cursor.moveToNext()) {
            list.add(
                ActivityModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
                )
            )
        }
        cursor.close()
        return list
    }
}
