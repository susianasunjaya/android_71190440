package id.ac.ukdw.pertemuan5_71190440

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")
        val tvGreetings = findViewById<TextView>(R.id.tvGreetings)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvGreetings.text = "Selamat Datang"
        tvUsername.text = username

    }
}