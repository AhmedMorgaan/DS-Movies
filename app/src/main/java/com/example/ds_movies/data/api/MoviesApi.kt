package com.example.ds_movies.data.api

import com.example.ds_movies.data.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    companion object {
        const val BASE_URL= "https://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
   suspend fun getAllResults (@Query("api_key") api_key:String) : Response<MoviesResponse>

}