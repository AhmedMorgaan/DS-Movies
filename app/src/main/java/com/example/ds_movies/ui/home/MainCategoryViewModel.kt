package com.example.ds_movies.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ds_movies.data.models.CategoryResponse
import com.example.ds_movies.data.models.ResultsItem
import com.example.ds_movies.data.repositories.MoviesRepository
import com.example.ds_movies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
   private val moviesRepository: MoviesRepository
):BaseViewModel() {

   private val _moviesCategory = MutableLiveData<MutableList<CategoryResponse.Genre>>()
           val moviesCategory : MutableLiveData<MutableList<CategoryResponse.Genre>> = _moviesCategory



     suspend fun getPopularMovies():MutableList<ResultsItem?>?{
         var movieslist:MutableList<ResultsItem?>? = null

            val response = moviesRepository.getPopularMovies()
            try {
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        progressBar.postValue(false)
                        movieslist = response.body()?.results
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressBar.postValue(false)
                    showMessage.value = e.localizedMessage
                    Log.e("Popular api Call Error", e.message.toString())
                }

            }

        return movieslist
    }

    suspend fun getMoviesWithCategoryId(genreId :Int):MutableList<ResultsItem?>? {
        var movieslist:MutableList<ResultsItem?>? = null

            val response = moviesRepository.getMoviesWithGenres(genreId)
            try {
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        progressBar.postValue(false)
                        movieslist = response.body()?.results
//                    _moviesList.value = response.body()?.results
                    }

                }

            }
            catch (e:Exception){
                withContext(Dispatchers.Main) {
                    progressBar.postValue(false)
                    showMessage.value = e.localizedMessage
                    Log.e("apiCallError",e.message.toString())
                }

            }

       return movieslist
    }

    fun getMoviesCategory(){
        viewModelScope.launch {
            val response = moviesRepository.getMoviesCategory()
            try {
            if (response.isSuccessful){
                withContext(Dispatchers.Main) {
                    progressBar.postValue(false)
                    _moviesCategory.value = response.body()?.genres
                }
            }
        }
            catch (e:Exception){
                withContext(Dispatchers.Main) {
                    progressBar.postValue(false)
                    showMessage.value = e.localizedMessage
                    Log.e("category",e.message.toString())
                }

        }
        }
    }

}