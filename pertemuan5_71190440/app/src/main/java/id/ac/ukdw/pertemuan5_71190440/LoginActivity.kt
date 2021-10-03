package id.ac.ukdw.pertemuan5_71190440

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            if(etPassword.text.toString().equals("12345")){
                toast("Login Berhasil!")
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", etUsername.text.toString())
                startActivity(intent)
            }else{
                toast("Login Gagal!")
            }
        }
    }
    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}