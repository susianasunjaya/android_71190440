package id.ac.ukdw.final_71190440

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.ukdw.final_71190440.databinding.ActivityCrudBinding
import id.ac.ukdw.final_71190440.fragments.HomeFragment

class DetailMovie : AppCompatActivity(R.layout.movie_detail) {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        val judul = intent.getStringExtra("judul")
        val tahun = intent.getStringExtra("tahun")
        val genre = intent.getStringExtra("genre")
        val duration = intent.getStringExtra("duration")
        val movieId = intent.getStringExtra("movieId")

        Toast.makeText(this,duration,Toast.LENGTH_SHORT).show()

        val tiJudul = findViewById<TextInputEditText>(R.id.ti_judul)
        val tiTahun = findViewById<TextInputEditText>(R.id.ti_tahun)
        val tiGenre = findViewById<TextInputEditText>(R.id.ti_genre)
        val tiDuration = findViewById<TextInputEditText>(R.id.ti_duration)
        val btn_edit = findViewById<Button>(R.id.btn_edit)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        val tv_movieId = findViewById<TextView>(R.id.tvMovieId)


        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
        tiJudul.text = judul?.toEditable()
        tiTahun.text = tahun?.toEditable()
        tiGenre.text = genre?.toEditable()
        tiDuration.text = duration?.toEditable()
        tv_movieId.text = movieId.toString()

//        if (cover != null) {
//            ivCover.setImageResource(cover)
//        }

        btn_delete.setOnClickListener {

            val movieId = tv_movieId.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Movie")
            database.child(movieId).removeValue()

            Toast.makeText(this,"Sukses Terhapus", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)


        }

        btn_edit.setOnClickListener {

            val movieId = tv_movieId.text.toString()
            val judul = tiJudul.text.toString().trim()
            val tahun = tiTahun.text.toString().trim()
            val genre = tiGenre.text.toString().trim()
            val duration = tiDuration.text.toString().trim()

            database = FirebaseDatabase.getInstance().getReference("Movie").child(movieId)
            database.child("judul").setValue(judul)
            database.child("tahun").setValue(tahun)
            database.child("genre").setValue(genre)
            database.child("duration").setValue(duration)

            Toast.makeText(this,"Sukses Terupdate", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}