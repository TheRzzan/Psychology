package com.morozov.psychology.utility

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.morozov.psychology.mvp.models.tests.ResultModel
import java.text.SimpleDateFormat
import java.util.*

class TestsResultsDBHelper(context: Context): SQLiteOpenHelper(context, TABLE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "TestsResults.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "TestsResults"

        const val _ID = "_id"
        const val COLUMN_DATE = "column_date"
        const val COLUMN_TEST_NAME = "column_test_name"
        const val COLUMN_RESULTS = "column_results"

        private val TEXT_TYPE = " TEXT"
        private val COMMA_SEP = ","

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_DATE +           TEXT_TYPE + COMMA_SEP +
                COLUMN_TEST_NAME +      TEXT_TYPE + COMMA_SEP +
                COLUMN_RESULTS +          TEXT_TYPE + ")"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME

        private const val SEPARATOR = "!separator!"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getCount(): Int {
        val db = readableDatabase
        val projection = arrayOf(_ID)
        val c = db.query(TABLE_NAME, projection, null, null, null, null, null)
        val count = c.count
        c.close()
        return count
    }

    fun getItemAt(position: Int): Pair<String, ResultModel>? {
        val db = readableDatabase
        val projection = arrayOf(
            _ID,
            COLUMN_DATE,
            COLUMN_TEST_NAME,
            COLUMN_RESULTS
        )
        val c = db.query(TABLE_NAME, projection, null, null, null, null, null)
        if (c.moveToPosition(position)) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss")

            val stringRes = c.getString(c.getColumnIndex(COLUMN_RESULTS))
            val split = stringRes.split(SEPARATOR)
            val list = mutableListOf<Pair<String, String>>()

            var i = 0
            while (i < split.size) {
                if (i + 1 >= split.size)
                    break

                val title = split[i]
                val description = split[i + 1]

                list.add(Pair(title, description))

                i += 2
            }

            val item = ResultModel(
                dateFormat.parse(c.getString(c.getColumnIndex(COLUMN_DATE))),
                list
            )

            val testName = c.getString(c.getColumnIndex(COLUMN_TEST_NAME))

            c.close()
            return Pair(testName, item)
        }
        return null
    }

    fun removeItemWithId(id: Int) {
        val db = writableDatabase
        val whereArgs = arrayOf(id.toString())
        db.delete(TABLE_NAME, "_ID=?", whereArgs)
    }

    fun removeItemWithDate(date: Date) {
        val db = writableDatabase

        val dateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss")
        val whereArgs = arrayOf(dateFormat.format(date))

        db.delete(TABLE_NAME, "COLUMN_DATE=?", whereArgs)
    }

    fun addResult(name: String, resultModel: ResultModel): Long {
        val db = writableDatabase
        val cv = ContentValues()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss")

        var strResult = ""

        for (item in resultModel.items) {
            strResult += "${item.first}$SEPARATOR${item.second}$SEPARATOR"
        }

        cv.put(COLUMN_DATE, dateFormat.format(resultModel.date))
        cv.put(COLUMN_TEST_NAME, name)
        cv.put(COLUMN_RESULTS, strResult)

        val rowId = db.insert(TABLE_NAME, null, cv)

        return rowId
    }
}