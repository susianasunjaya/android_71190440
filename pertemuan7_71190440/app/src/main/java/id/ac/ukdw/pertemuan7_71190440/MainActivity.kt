package id.ac.ukdw.pertemuan7_71190440

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = ArrayList<Contact>()
        listContact.add(Contact("Jang Wonyoung", "0812328789", R.mipmap.jangwonyoung_round))
        listContact.add(Contact("Jung Jaehyun", "0845656789", R.mipmap.jungjaehyun_round))
        listContact.add(Contact("Karina", "0879654215", R.mipmap.karina_round))
        listContact.add(Contact("Kim Jennie", "088855559999", R.mipmap.jennie_round))
        listContact.add(Contact("Kim Seonho", "085278945886", R.mipmap.kimseonho_round))
        listContact.add(Contact("Naruto", "08123456789", R.mipmap.naruto_round))
        listContact.add(Contact("So Junghwan", "08198765432", R.mipmap.sojunghwan_round))


        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)

        val adapter = ContactAdapter(listContact)
        rvContact.adapter = adapter


    }
}