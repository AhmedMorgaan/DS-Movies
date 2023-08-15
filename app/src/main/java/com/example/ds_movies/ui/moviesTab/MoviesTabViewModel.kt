package com.example.ds_movies.ui.moviesTab

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ds_movies.core.Result
import com.example.ds_movies.data.models.Genre
import com.example.ds_movies.data.models.MovieItem
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

    private val _topRatedMoviesList = MutableLiveData<MutableList<MovieItem?>?>()
    val topRatedMoviesList = _topRatedMoviesList


     fun getMoviesCategories(){
        viewModelScope.launch {
            progressBar.value = true
           val result =  moviesRepository.getMoviesCategoryWithBase()
            when (result) {
                is Result.Success -> {
                    progressBar.value = false
                    _genresList.value = result.data.genres
                }
                is Result.Error -> {
                    progressBar.value = false
                    showMessage.value = result.exception.message
                }
                is Result.Loading -> {
                    progressBar.value = true
                }
            }
        }
    }
    fun getTopRatedMovies(genreId:Int?){
        viewModelScope.launch {
            progressBar.value = true
            val result =  moviesRepository.getTopRatedMovies()
            when (result) {
                is Result.Success -> {
                    progressBar.value = false
                    val results = result.data.results

                    if (genreId !=null){
                        val listFilter = results?.filter {
                            it?.genreIds!!.contains(genreId)
                        }?.toMutableList()
                        _topRatedMoviesList.value = listFilter
                        Log.e("filter", "getTopRatedMovies: $listFilter", )
                    }
                    else{
                        _topRatedMoviesList.value = result.data.results
                        Log.e("topMovies", "getTopRatedMovies: ${result.data.results}", )
                    }
                }
                is Result.Error -> {
                    progressBar.value = false
                    showMessage.value = result.exception.message
                }
                is Result.Loading -> {
                    progressBar.value = true
                }
            }
        }
    }
}
