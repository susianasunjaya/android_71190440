package id.ac.ukdw.pertemuan11_71190440

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()


        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val txvOutput = findViewById<TextView>(R.id.txvOutput)
        val btnRadio = findViewById<RadioGroup>(R.id.btnRadio)
        val btnAsc = findViewById<RadioButton>(R.id.btnAsc)
        val btnDesc = findViewById<RadioButton>(R.id.btnDesc)

        var valueRadio = ""


        btnSimpan.setOnClickListener{
            val mahasiswa = Mahasiswa(edtNama.text.toString(), edtNim.text.toString(),edtIpk.text.toString().toDouble())
            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            firestore?.collection("mahasiswa")?.add(mahasiswa)

        }

        btnRadio.setOnCheckedChangeListener{
                group,checkedId->
            valueRadio = when(checkedId){
                R.id.btnAsc -> "Ascending"
                R.id.btnDesc -> "Descending"
                else -> ""
            }
        }

        btnCari.setOnClickListener{
            if(valueRadio.equals("Ascending")){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.orderBy("ipk",Query.Direction.ASCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(dsc in docs){
                            output += "\n Nama: ${dsc["nama"]}\n NIM: ${dsc["nim"]}\n IPK : ${dsc["ipk"]}\n"

                            txvOutput.setText(output)
                            btnAsc.isChecked = false
                        }
                    }
            }
            else if(valueRadio.equals("Descending")){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(dsc in docs){
                            output += "\n Nama: ${dsc["nama"]}\n NIM: ${dsc["nim"]}\n IPK : ${dsc["ipk"]}\n"

                            txvOutput.setText(output)
                            btnDesc.isChecked = false
                        }
                    }
            }
            else{
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output  = ""
                        for(doc in docs){
                            output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"
                        }
                        txvOutput.setText(output)
                    }

            }
        }

    }
}