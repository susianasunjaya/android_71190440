package id.ac.ukdw.pertemuan10_71190440

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db =DatabaseHelper(this).writableDatabase
        val etNama = findViewById<EditText>(R.id.etNama)
        val etUsia = findViewById<EditText>(R.id.etUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)


        btnSimpan.setOnClickListener(){
            insertData(Penduduk(etNama.text.toString(), etUsia.text.toString().toInt()))
            etNama.setText("")
            etUsia.setText("")
        }

        btnHapus.setOnClickListener(){
            deleteData(Penduduk(etNama.text.toString(), etUsia.text.toString().toInt()))
            etNama.setText("")
            etUsia.setText("")
        }

        btnCari.setOnClickListener(){
            cariData(Penduduk(etNama.text.toString(), etUsia.text.toString().toInt()))
            etNama.setText("")
            etUsia.setText("")
        }

        getData()

    }

    fun insertData(penduduk: Penduduk){
        val values = ContentValues().apply {
            put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, penduduk.nama)
            put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, penduduk.usia)
        }
        db.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
        getData()
    }

    fun deleteData(penduduk: Penduduk){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(penduduk.nama, penduduk.usia.toString())
        db.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)
        getData()
    }

    fun cariData(penduduk: Penduduk){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(penduduk.nama, penduduk.usia.toString())

        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var result = ""
        with(cursor) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0), getInt(1))
                result += "${penduduk.nama} - ${penduduk.usia}\n"
            }
        }
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        tvHasil.text = result
    }

    fun getData(){
        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        var result = ""
        with(cursor) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0), getInt(1))
                result += "${penduduk.nama} - ${penduduk.usia}\n"
            }
        }
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        tvHasil.text = result

    }
}