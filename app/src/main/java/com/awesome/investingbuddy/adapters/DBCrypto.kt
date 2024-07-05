package com.awesome.investingbuddy.adapters

import com.awesome.investingbuddy.ui.cryptodatbasecreator
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBCrypto(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertData(Total: String?, Invested: String?, Name:String?): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DBContract2.DBEntry.COLUMN_Total, Total)
        values.put(DBContract2.DBEntry.COLUMN_Invested, Invested)
        values.put(DBContract2.DBEntry.COLUMN_Name, Name)

        return db.insert(DBContract2.DBEntry.TABLE_NAME, null, values)
    }
    fun del(){

        var db=this.writableDatabase
        db.execSQL("delete from " + DBContract2.DBEntry.TABLE_NAME);

        db.close()


    }
    fun updateSpec(sector: String, newInvestment: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBContract2.DBEntry.COLUMN_Invested, newInvestment)

        val updateResult = db.update(DBContract2.DBEntry.TABLE_NAME, contentValues, DBContract2.DBEntry.COLUMN_Name+"=?", arrayOf(sector))
        db.close()

        return updateResult > 0
    }
    @SuppressLint("SuspiciousIndentation")
    fun delspec(sector:String):Int{
        var db=this.writableDatabase
        return db.delete( DBContract2.DBEntry.TABLE_NAME, DBContract2.DBEntry.COLUMN_Name+"=?",arrayOf(sector));
        db.close()

    }



    fun readData(): Cursor {
        val db = this.readableDatabase
        val projection = arrayOf(
            DBContract2.DBEntry.COLUMN_Name,
            DBContract2.DBEntry.COLUMN_Invested,
            DBContract2.DBEntry.COLUMN_Total,

            )
        val cursor = db.query(
            DBContract2.DBEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
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
        const val DATABASE_NAME = "DB.db2"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DBContract2.DBEntry.TABLE_NAME} (" +
                    "${DBContract2.DBEntry._ID} INTEGER PRIMARY KEY," +
                    "${DBContract2.DBEntry.COLUMN_Total} TEXT," +
                    "${DBContract2.DBEntry.COLUMN_Invested} TEXT," +
                    "${DBContract2.DBEntry.COLUMN_Name} TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBContract.DBEntry.TABLE_NAME}"
    }
}

object DBContract2 {
    object DBEntry {
        const val TABLE_NAME = "entry"
        const val _ID = "_id"
        const val COLUMN_Total = "Total"
        const val COLUMN_Invested = "Investmnet"
        const val COLUMN_Name = "Name"

    }
}
