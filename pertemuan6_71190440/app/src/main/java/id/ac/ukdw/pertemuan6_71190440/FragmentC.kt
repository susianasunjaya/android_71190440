package id.ac.ukdw.pertemuan6_71190440

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentC: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val i = inflater.inflate(R.layout.fragment_c, container, false)
        val btnLogin = i.findViewById<Button>(R.id.buttonC)

        btnLogin.setOnClickListener {
            val intent = Intent(context, Halaman1::class.java)
            startActivity(intent)
        }
        return i
    }
}