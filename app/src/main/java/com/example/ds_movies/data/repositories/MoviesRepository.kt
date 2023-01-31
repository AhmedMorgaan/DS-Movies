package com.example.ds_movies.data.repositories

import com.example.ds_movies.data.api.MoviesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(val moviesApi :MoviesApi) {

}