package com.example.ds_movies.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ds_movies.data.models.CategoryResponse
import com.example.ds_movies.data.models.ResultsItem
import com.example.ds_movies.data.repositories.MoviesRepository
import com.example.ds_movies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
   private val moviesRepository: MoviesRepository
):BaseViewModel() {

   private val _moviesCategory = MutableLiveData<MutableList<CategoryResponse.Genre>>()
           val moviesCategory : MutableLiveData<MutableList<CategoryResponse.Genre>> = _moviesCategory

    private val _moviesList = MutableLiveData<MutableList<ResultsItem?>?>()
    val moviesList : MutableLiveData<MutableList<ResultsItem?>?> = _moviesList



    fun getMoviesWithId(genreId: Int) = viewModelScope.async {
        getMoviesWithCategoryId(genreId)
    }


    suspend fun getMoviesWithCategoryId(genreId :Int):MutableList<ResultsItem?>? {
        var movieslist:MutableList<ResultsItem?>? = null

            val response = moviesRepository.getMoviesWithGenres(genreId)
            try {
                if (response.isSuccessful){
                   movieslist = response.body()?.results
                    _moviesList.value = response.body()?.results
                }

            }
            catch (e:Exception){
                Log.e("apiCallError",e.message.toString())
            }

       return movieslist
    }

    fun getMoviesCategory(){
        viewModelScope.launch {
            val response = moviesRepository.getMoviesCategory()
            try {
            if (response.isSuccessful){
                _moviesCategory.value = response.body()?.genres
            }
        }
            catch (e:Exception){
            Log.e("category",e.message.toString())
        }
        }
    }

}