package id.ac.ukdw.final_71190440.fragments

import android.content.Intent
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
import android.widget.Button
import android.widget.TextView


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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            binding = ActivityProfileBinding.inflate(layoutInflater)
//            setContentView(binding.root)

            //init firebase auth
            firebaseAuth = FirebaseAuth.getInstance()
            checkUser()

            //handle click, logout user
            val i = inflater.inflate(R.layout.fragment_profile, container, false)
            val logoutBtn = i.findViewById<Button>(R.id.logoutBtn)
//            binding.
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
        }else{
            //user logged in
            //get user info
            val email = firebaseUser.email
            //set email

//            binding.emailTv.text=email
            activity?.findViewById<TextView>(R.id.emailTv)?.text = email
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