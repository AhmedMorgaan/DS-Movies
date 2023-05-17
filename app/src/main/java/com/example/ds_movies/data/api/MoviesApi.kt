package com.example.ds_movies.data.api

import com.example.ds_movies.data.models.CategoryResponse
import com.example.ds_movies.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies () : Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategory() : Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getMoviesWithGenres(@Query("with_genres") genreId:Int): Response<MoviesResponse>
}