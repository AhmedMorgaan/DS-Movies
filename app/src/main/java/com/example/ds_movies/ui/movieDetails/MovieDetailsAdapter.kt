package com.example.ds_movies.ui.movieDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ds_movies.R
import com.example.ds_movies.core.utils.Constant.Companion.BASE_POSTER_IMAGE_URL
import com.example.ds_movies.data.models.ResultsItem

class MovieDetailsAdapter(private var movies :MutableList<ResultsItem?>?)
    :RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View =LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val movie = movies?.get(position)
        Glide
            .with(holder.itemView)
            .load(BASE_POSTER_IMAGE_URL+movie?.posterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.movieImage)
        holder.movieTitle.text = movie?.title
        holder.movieDescription.text = movie?.overview
        holder.movieReleaseDate.text = movie?.releaseDate
        holder.movieVoteRate.text = movie?.voteAverage.toString()
        holder.movieTrailer.setOnClickListener {
            //onItemClickListener?.onItemClick(position, movies)
            try {
                it.findNavController()
                    .navigate(R.id.action_movieDetailsFragment_to_videoTrailerFragment)
            }catch (e:Exception){
                e.localizedMessage?.let { it1 -> Log.e("here", it1) }
            }
        }
    }

    override fun getItemCount(): Int {
        return movies?.size?:0
    }

    var onItemClickListener : OnItemClickListener? = null
    interface OnItemClickListener{
        fun onItemClick(pos:Int,item:MutableList<ResultsItem?>?)
    }

    fun changeData (List:MutableList<ResultsItem?>?){
        this.movies=List
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var movieImage :ImageView
        var movieTitle :TextView
        var movieDescription :TextView
        var movieReleaseDate :TextView
        var movieVoteRate :TextView
        var movieTrailer :TextView

        init {
            movieTrailer = itemView.findViewById(R.id.btn_movie_trailer)
            movieImage = itemView.findViewById(R.id.movie_image)
            movieTitle = itemView.findViewById(R.id.movie_title)
            movieDescription = itemView.findViewById(R.id.movie_description)
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date)
            movieVoteRate = itemView.findViewById(R.id.movie_vote_rate)
        }

    }

}