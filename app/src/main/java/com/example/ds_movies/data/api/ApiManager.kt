package com.example.ds_movies.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager  {


    companion object {

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstance(): MoviesApi {
            return retrofit.create(MoviesApi::class.java)
        }
    }
}

//       // private val interceptor: Interceptor = Interceptor { chain ->
//            val request = chain.request()
//                .newBuilder()
//                .addHeader("api_key", Constant.apiKay)
//                .build()
//            chain.proceed(request)
//        }

// private val client:OkHttpClient  = OkHttpClient.Builder().addInterceptor(interceptor)
// private val client = OkHttpClient()
