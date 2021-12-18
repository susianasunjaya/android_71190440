package id.ac.ukdw.final_71190440.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import id.ac.ukdw.final_71190440.MainActivity
import id.ac.ukdw.final_71190440.R
import id.ac.ukdw.final_71190440.databinding.ActivityProfileBinding
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ac.ukdw.final_71190440.Movie
import id.ac.ukdw.final_71190440.ProfileActivity
import java.net.URI


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    //view binding
    private lateinit var binding: ActivityProfileBinding

    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    private var infoList:ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
//            binding = ActivityProfileBinding.inflate(layoutInflater, container, false)
//            Ini di fragment jadi gabisa huhu setContentView(binding.root)
//            super.onViewCreated(binding.root,savedInstanceState)



        //init firebase auth
            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()


            //handle click, logout user
            val i = inflater.inflate(R.layout.fragment_profile, container, false)
            val logoutBtn = i.findViewById<Button>(R.id.logoutBtn)
            val emailTv = i.findViewById<TextView>(R.id.emailTv)
            val uphoto = i.findViewById<ImageView>(R.id.ivProfile)
            val dname = i.findViewById<TextView>(R.id.nameTv)
            checkUserLogin()

            emailTv.setText(infoList.get(0))
            dname.setText(infoList.get(1))
            val imgUrl: String = infoList.get(2)

            Glide.with(this).load(imgUrl).apply(RequestOptions.circleCropTransform()).into(uphoto)

//            uphoto.setImageURI(null)
//            uphoto.setImageURI(imgUrl.toUri())
//            uphoto.setImageURI(Uri.parse(infoList.get(1)))

            logoutBtn.setOnClickListener{
                firebaseAuth.signOut()
                checkUser()
        }
        return i
    }

    private fun checkUser() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser==null){
            //user not logged in
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
//        }else{
//            //user logged in
//            //get user info
//            val email = firebaseUser.email
//            val uphoto = firebaseUser.photoUrl
            //set email
//            emailTv.text = email.toString()
//            activity?.findViewById<TextView>(R.id.emailTv)?.text = (email.toString())
//            activity?.findViewById<ImageView>(R.id.ivProfile)?.setImageURI(uphoto)
        }
    }

    private fun checkUserLogin() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        infoList.clear()

        if (firebaseUser!=null){
            //user not logged in
            val email = firebaseUser.email
            val dname = firebaseUser.displayName
            val uphoto = firebaseUser.photoUrl
            infoList.add(email.toString())
            infoList.add(dname.toString())
            infoList.add(uphoto.toString())
//            Toast.makeText(activity,uphoto.toString(),Toast.LENGTH_SHORT).show()
        }
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ProfileFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProfileFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}