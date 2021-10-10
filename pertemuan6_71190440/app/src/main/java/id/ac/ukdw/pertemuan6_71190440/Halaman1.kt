package id.ac.ukdw.pertemuan6_71190440

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

class Halaman1 : AppCompatActivity(R.layout.halaman1) {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.halaman1)
    }
}