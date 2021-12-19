package id.ac.ukdw.final_71190440.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import id.ac.ukdw.final_71190440.CrudActivity
import id.ac.ukdw.final_71190440.Movie
import id.ac.ukdw.final_71190440.MovieAdapter
import id.ac.ukdw.final_71190440.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var dbref: DatabaseReference
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieArrayList : ArrayList<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val i = inflater.inflate(R.layout.fragment_home, container, false)
        val addBtn = i.findViewById<FloatingActionButton>(R.id.fab_add)

        movieRecyclerView = i.findViewById(R.id.rv_movie)
        movieRecyclerView.setHasFixedSize(true)
        movieArrayList = arrayListOf<Movie>()


        movieRecyclerView.adapter = MovieAdapter(movieArrayList)
        getMovieData()

        addBtn.setOnClickListener {
            val intent = Intent(context, CrudActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return i
    }



    private fun getMovieData() {

        dbref = FirebaseDatabase.getInstance().getReference("Movie")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var movieList:ArrayList<Movie> = ArrayList()
                movieList.clear()

                if(snapshot.exists()){
                    for (movieSnapshot in snapshot.children){
                        val movie = movieSnapshot.getValue(Movie::class.java)
                        movieList.add(movie!!)
                    }
                    var movieAdapter = MovieAdapter(movieList)
                    movieRecyclerView.apply {
                        movieRecyclerView.layoutManager = LinearLayoutManager(context)
                        movieRecyclerView.adapter = movieAdapter
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HomeFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
