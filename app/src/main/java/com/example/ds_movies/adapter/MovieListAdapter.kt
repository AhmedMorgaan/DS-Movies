package com.example.ds_movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ds_movies.model.ResultsItem
import com.example.ds_movies.R

class MovieListAdapter(private var movies :MutableList<ResultsItem?>?) :RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View =LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return ViewHolder(view)
    }
    //https://miro.medium.com/max/1200/1*jYvicNC-2KCMmkzeuh0Hwg.png
   // https://api.themoviedb.org/3/movie/popular?6PFJrMvoQwBxQITLYHj09VeJ37q.jpg
    //https://api.themoviedb.org/3/movie/popular?api_key=7ebfbb8ac4704697733b140b8d5e1e59&poster_path=nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg
    //"https://api.themoviedb.org/3/movie/popular"+movie?.posterPath)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val movie = movies?.get(position)
        Glide
            .with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movie?.posterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.movieImage)
        movie?.posterPath?.let { Log.e("path", "https://image.tmdb.org/t/p/w500"+movie?.posterPath) }
        holder.movieTitle.text = movie?.title
        holder.movieDescription.text = movie?.overview
        holder.movieReleaseDate.text = movie?.releaseDate
        holder.movieVoteRate.text = movie?.voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return movies?.size?:0
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

        init {
            movieImage = itemView.findViewById(R.id.movie_image)
            movieTitle = itemView.findViewById(R.id.movie_title)
            movieDescription = itemView.findViewById(R.id.movie_description)
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date)
            movieVoteRate = itemView.findViewById(R.id.movie_vote_rate)
        }

    }

}