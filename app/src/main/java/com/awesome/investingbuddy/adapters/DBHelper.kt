package com.awesome.investingbuddy.adapters

import com.awesome.investingbuddy.ui.Dbcheck
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertData(name: String?, age: String?, gender:String?, address: String?): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME, name)
        values.put(DBContract.DBEntry.COLUMN_NAME_AGE, age)
        values.put(DBContract.DBEntry.COLUMN_NAME_GENDER, gender)
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, address)
        return db.insert(DBContract.DBEntry.TABLE_NAME, null, values)
    }
    fun del(){

        var db=this.writableDatabase
        db.execSQL("delete from " + DBContract.DBEntry.TABLE_NAME);

        db.close()


    }
    fun updateSpec(sector: String, newInvestment: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS, newInvestment)

        val updateResult = db.update(DBContract.DBEntry.TABLE_NAME, contentValues, DBContract.DBEntry.COLUMN_NAME_AGE+"=?", arrayOf(sector))
        db.close()

        return updateResult > 0
    }
    @SuppressLint("SuspiciousIndentation")
    fun delspec(sector:String):Int{
        var db=this.writableDatabase
        return db.delete( DBContract.DBEntry.TABLE_NAME, DBContract.DBEntry.COLUMN_NAME_AGE+"=?",arrayOf(sector));


    }
    fun upgradespec(sectorprev: String,sectornew:String,nametot:String,gendercolr:String,addrselA:String):Int{
        var db=this.writableDatabase
        val values=ContentValues();
        values.put(DBContract.DBEntry.COLUMN_NAME_AGE,sectornew)
        values.put(DBContract.DBEntry.COLUMN_NAME_NAME,nametot);
        values.put(DBContract.DBEntry.COLUMN_NAME_GENDER,gendercolr)
        values.put(DBContract.DBEntry.COLUMN_NAME_ADDRESS,addrselA)
        return db.update(DBContract.DBEntry.TABLE_NAME,values,DBContract.DBEntry.COLUMN_NAME_AGE+"=?",
            arrayOf(sectorprev)
        )

    }


    fun readData(): Cursor {
        val db = this.readableDatabase
        val projection = arrayOf(
            DBContract.DBEntry.COLUMN_NAME_NAME,
            DBContract.DBEntry.COLUMN_NAME_AGE,
            DBContract.DBEntry.COLUMN_NAME_GENDER,
            DBContract.DBEntry.COLUMN_NAME_ADDRESS
        )
        val cursor = db.query(
            DBContract.DBEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val data = ArrayList<String>()
        /*while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntry.COLUMN_NAME_NAME))
            val age = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntry.COLUMN_NAME_AGE))
            val gender = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntry.COLUMN_NAME_GENDER))
            val address = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.DBEntry.COLUMN_NAME_ADDRESS))
            data.add("$name $age $gender $address")
        }
        cursor.close()*/
        return cursor;
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DB.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DBContract.DBEntry.TABLE_NAME} (" +
                    "${DBContract.DBEntry._ID} INTEGER PRIMARY KEY," +
                    "${DBContract.DBEntry.COLUMN_NAME_NAME} TEXT," +
                    "${DBContract.DBEntry.COLUMN_NAME_AGE} TEXT," +
                    "${DBContract.DBEntry.COLUMN_NAME_GENDER} INTEGER," +
                    "${DBContract.DBEntry.COLUMN_NAME_ADDRESS} TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBContract.DBEntry.TABLE_NAME}"
    }
}

object DBContract {
    object DBEntry {
        const val TABLE_NAME = "entry"
        const val _ID = "_id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_AGE = "age"
        const val COLUMN_NAME_GENDER = "gender"
        const val COLUMN_NAME_ADDRESS = "address"
    }
}
