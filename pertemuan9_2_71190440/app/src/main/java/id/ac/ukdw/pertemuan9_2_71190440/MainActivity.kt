package id.ac.ukdw.pertemuan9_2_71190440

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("mySP", Context.MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){
            //sudah login
            setContentView(R.layout.activity_home)
            val spBahasa = findViewById<Spinner>(R.id.spBahasa)
            val adapter = ArrayAdapter.createFromResource(this, R.array.list_bahasa, R.layout.support_simple_spinner_dropdown_item)
            spBahasa.adapter = adapter
            spBahasa.setSelection(sp!!.getInt("bahasa",0))
            spBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    spEdit?.putInt("bahasa",position)
                    spEdit?.commit()

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }


            val etUkuran = findViewById<EditText>(R.id.etUkuran)
            etUkuran.setText(sp!!.getString("ukuran","10"))
            etUkuran.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran",s.toString())
                    spEdit?.commit()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
            })


        }else{
            //belum login
            setContentView(R.layout.activity_login)
            val etUsername = findViewById<EditText>(R.id.etUsername)
            val etPassword = findViewById<EditText>(R.id.etPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener {
                if(etUsername.text.toString()=="admin" && etPassword.text.toString().equals("1234")){
                    spEdit?.putBoolean("isLogin", true)
                    spEdit?.commit()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()

                }else{
                    toast("Login Gagal!")
                }
            }
        }

    }
    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}