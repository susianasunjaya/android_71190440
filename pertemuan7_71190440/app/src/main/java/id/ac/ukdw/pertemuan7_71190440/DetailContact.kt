package id.ac.ukdw.pertemuan7_71190440

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailContact: AppCompatActivity(R.layout.detail_contact) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_contact)

        val nama = intent.getStringExtra("nama")
        val nomor = intent.getStringExtra("nomor")
        val cover = intent.getIntExtra("cover", 0)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvPhoneNumber = findViewById<TextView>(R.id.tvPhoneNumber)
        val ivCover = findViewById<ImageView>(R.id.ivCover)

        tvName.text = nama
        tvPhoneNumber.text = nomor
        if (cover != null) {
            ivCover.setImageResource(cover)
        }
    }
}


