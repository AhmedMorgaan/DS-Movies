package com.example.ds_movies.data.repositories

import com.example.ds_movies.data.api.MoviesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val moviesApi :MoviesApi) {

    suspend fun getPopularMovies () = moviesApi.getPopularMovies()
    suspend fun getMoviesCategory() = moviesApi.getMoviesCategory()
    suspend fun getMoviesWithGenres(genreId:Int) = moviesApi.getMoviesWithGenres(genreId)


}