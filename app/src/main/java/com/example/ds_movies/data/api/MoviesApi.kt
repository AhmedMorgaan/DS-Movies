package com.example.ds_movies.data.api

import com.example.ds_movies.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/popular")
   suspend fun getPopularMovies () : Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies (@Query("api_key")aki_key:String) : Response<MoviesResponse>

}