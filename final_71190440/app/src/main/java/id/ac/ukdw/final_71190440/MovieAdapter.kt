package id.ac.ukdw.final_71190440

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.final_71190440.databinding.ItemMovieBinding
import id.ac.ukdw.final_71190440.fragments.HomeFragment

class MovieAdapter(private val movieList:ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val movieView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieViewHolder(movieView)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindMovie(movieList[position])

        }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(val movieView : View):RecyclerView.ViewHolder(movieView){
        val judul: TextView = movieView.findViewById(R.id.tv_judul)
        val tahun : TextView = movieView.findViewById(R.id.tv_tahun)
        val genre : TextView = movieView.findViewById(R.id.tv_genre)
        val duration : TextView = movieView.findViewById(R.id.tv_duration)
        val movieId : TextView = movieView.findViewById(R.id.tvMovieId)

        fun bindMovie(movie: Movie){
            judul.text = movie.Judul
            tahun.text = movie.Tahun
            genre.text = movie.Genre
            duration.text = movie.Duration
            movieId.text = movie.movieId

            movieView.setOnClickListener{
                val intent : Intent = Intent(movieView.context, DetailMovie::class.java)
                intent.putExtra("judul",movie.Judul)
                intent.putExtra("tahun",movie.Tahun)
                intent.putExtra("genre",movie.Genre)
                intent.putExtra("duration",movie.Duration)
                intent.putExtra("movieId",movie.movieId)

                movieView.context.startActivity(intent)
            }


        }

    }

}



