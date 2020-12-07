package com.example.noforeignland_exam_pgr208.data.db.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.noforeignland_exam_pgr208.data.db.Table
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_ID
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LAT
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LAT_TYPE
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LNG
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_LNG_TYPE
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_PLACE_ID
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_NAME
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_NAME_TYPE
import com.example.noforeignland_exam_pgr208.data.db.Table.COLUMN_PLACE_ID_TYPE

class DatabaseHandler(ctx: Context) : SQLiteOpenHelper(
    ctx,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ${Table.PLACES_TABLE_NAME} " +
                    "(" +
                    " $COLUMN_ID INTEGER PRIMARY KEY, " +
                    " $COLUMN_PLACE_ID $COLUMN_PLACE_ID_TYPE NOT NULL UNIQUE, " +
                    " $COLUMN_NAME $COLUMN_NAME_TYPE, " +
                    " $COLUMN_LAT $COLUMN_LAT_TYPE," +
                    " $COLUMN_LNG $COLUMN_LNG_TYPE" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private var mInstance: DatabaseHandler? = null
        private const val DATABASE_NAME = "databaseName"
        private const val DATABASE_VERSION = 1
        fun getInstance(ctx: Context): DatabaseHandler? {
            if (mInstance == null) {
                mInstance =
                    DatabaseHandler(
                        ctx.applicationContext
                    )
            }
            return mInstance
        }
    }

}

