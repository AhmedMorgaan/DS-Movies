package com.example.ds_movies.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ds_movies.core.Result
import com.example.ds_movies.data.models.Genre
import com.example.ds_movies.data.repositories.MoviesRepository
import com.example.ds_movies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesTabViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
):BaseViewModel() {

    private val _genresList = MutableLiveData<MutableList<Genre>>()
    val genresList = _genresList

    init {
        getMovies()
    }

     private fun getMovies(){
        viewModelScope.launch {
           val result =  moviesRepository.getMoviesCategoryWithBase()
            when (result) {
                is Result.Success -> {
                    Log.e("Result Data", "getMovies: ${result.data}" )
//                    withContext(Dispatchers.Main){
                        _genresList.value = result.data.genres
//                    }
                }
                is Result.Error -> {
                    Log.e("Result message", "getMovies: ${result.exception.message}" )
                    Log.e("Result localizedMessage", "getMovies: ${result.exception.localizedMessage}" )
                    Log.e("Result exception", "getMovies: ${result.exception}" )
                }

                else -> {
                    Log.e("Result Else", "getMovies: Else" )
                }
            }
        }

    }
}