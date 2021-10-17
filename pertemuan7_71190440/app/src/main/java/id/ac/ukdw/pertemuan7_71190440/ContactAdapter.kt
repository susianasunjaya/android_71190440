package id.ac.ukdw.pertemuan7_71190440

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivities
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val listContact: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    class ContactHolder(val v: View): RecyclerView.ViewHolder(v){
        fun bindView(contact: Contact){
            v.findViewById<TextView>(R.id.tvName).text = contact.nama
            v.findViewById<TextView>(R.id.tvPhoneNumber).text = contact.nomor
            v.findViewById<ImageView>(R.id.ivCover).setImageResource(contact.cover)
            v.setOnClickListener{
                val intent : Intent = Intent(v.context, DetailContact::class.java)
                intent.putExtra("nama",contact.nama)
                intent.putExtra("nomor",contact.nomor)
                intent.putExtra("cover",contact.cover)
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactHolder(v)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactHolder, position: Int) {

        holder.bindView(listContact[position])
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}