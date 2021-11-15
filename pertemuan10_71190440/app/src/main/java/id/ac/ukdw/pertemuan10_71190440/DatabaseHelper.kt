package id.ac.ukdw.pertemuan10_71190440

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DatabaseContract.Penduduk.SQL_CREATE_TABLE)
        }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DatabaseContract.Penduduk.SQL_DELETE_TABLE)
        onCreate(db)
        }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
        }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "myDB.db"
        }
}