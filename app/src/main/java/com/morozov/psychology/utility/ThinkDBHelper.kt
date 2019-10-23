package com.morozov.psychology.utility

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.morozov.psychology.mvp.models.diary.EmotionModel
import com.morozov.psychology.mvp.models.diary.ThinkModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ThinkDBHelper(context: Context): SQLiteOpenHelper(context, TABLE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "think.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "think"

        const val _ID = "_id"
        const val COLUMN_DATE = "column_date"
        const val COLUMN_SITUATION = "column_situation"
        const val COLUMN_THINK = "column_think"
        const val COLUMN_EMOTIONS = "column_emotions"
        const val COLUMN_SENSATION = "column_sensation"
        const val COLUMN_IS_OVERWRITED = "column_is_overwrited"

        private val TEXT_TYPE = " TEXT"
        private val BOOL_TYPE = " BOOLEAN"
        private val COMMA_SEP = ","

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_DATE +           TEXT_TYPE + COMMA_SEP +
                COLUMN_SITUATION +      TEXT_TYPE + COMMA_SEP +
                COLUMN_THINK +          TEXT_TYPE + COMMA_SEP +
                COLUMN_SENSATION +      TEXT_TYPE + COMMA_SEP +
                COLUMN_IS_OVERWRITED +  BOOL_TYPE + COMMA_SEP +
                COLUMN_EMOTIONS +       TEXT_TYPE + ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
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

    fun getItemAt(position: Int): ThinkModel? {
        val db = readableDatabase
        val projection = arrayOf(
            _ID,
            COLUMN_DATE,
            COLUMN_SITUATION,
            COLUMN_THINK,
            COLUMN_SENSATION,
            COLUMN_IS_OVERWRITED,
            COLUMN_EMOTIONS
        )
        val c = db.query(TABLE_NAME, projection, null, null, null, null, null)
        if (c.moveToPosition(position)) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss")

            val mutableListOf = mutableListOf<EmotionModel>()
            val stringEmot = c.getString(c.getColumnIndex(COLUMN_EMOTIONS))
            val listEmot = stringEmot.split(" ")

            var i = 0

            while (i < listEmot.size) {
                if (i + 1 >= listEmot.size)
                    break

                val emot = listEmot[i]
                val percent = listEmot[i + 1].toInt()

                when (emot) {
                    EmotionModel.Emotion.JOY.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.JOY, percent))
                    EmotionModel.Emotion.SADNESS.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.SADNESS, percent))
                    EmotionModel.Emotion.ANNOYANCE.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.ANNOYANCE, percent))
                    EmotionModel.Emotion.ANXIETY.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.ANXIETY, percent))
                    EmotionModel.Emotion.DISGUST.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.DISGUST, percent))
                    EmotionModel.Emotion.INTEREST.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.INTEREST, percent))
                    EmotionModel.Emotion.GUILT.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.GUILT, percent))
                    EmotionModel.Emotion.RESENTMENT.name -> mutableListOf.add(EmotionModel(EmotionModel.Emotion.RESENTMENT, percent))
                }

                i += 2
            }

            val item = ThinkModel(
                dateFormat.parse(c.getString(c.getColumnIndex(COLUMN_DATE))),
                c.getString(c.getColumnIndex(COLUMN_SITUATION)),
                c.getString(c.getColumnIndex(COLUMN_THINK)),
                ArrayList(mutableListOf),
                c.getString(c.getColumnIndex(COLUMN_SENSATION)),
                c.getInt(c.getColumnIndex(COLUMN_IS_OVERWRITED)) == 1
            )
            c.close()
            return item
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

    fun addThink(thinkModel: ThinkModel): Long {
        val db = writableDatabase
        val cv = ContentValues()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy/HH:mm:ss")

        var emotions = ""

        for (item in thinkModel.emotion) {
            emotions += "${item.emotion.name} ${item.percent} "
        }

        cv.put(COLUMN_DATE, dateFormat.format(thinkModel.date))
        cv.put(COLUMN_SITUATION, thinkModel.situation)
        cv.put(COLUMN_THINK, thinkModel.think)
        cv.put(COLUMN_SENSATION, thinkModel.sensation)
        cv.put(COLUMN_IS_OVERWRITED, thinkModel.isOverwrited)
        cv.put(COLUMN_EMOTIONS, emotions)

        val rowId = db.insert(TABLE_NAME, null, cv)

        return rowId
    }
}