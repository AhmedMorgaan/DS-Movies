package com.example.ds_movies.api

import com.example.ds_movies.model.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WepServices {

    @GET("movie/popular")
   suspend fun getAllResults (@Query("api_key") api_key:String) : Response<MoviesResponse>

   suspend fun testCoroutine (number:Int){
       val sum = number+(number*2)
   }
}