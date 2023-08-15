package com.example.ds_movies.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import com.example.ds_movies.data.models.MovieItem
import com.example.ds_movies.data.repositories.MoviesRepository
import com.example.ds_movies.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : BaseViewModel() {

    val resultLiveData = MutableLiveData<MutableList<MovieItem?>?>()
    var progressBarLiveData = MutableLiveData<Boolean>()

    fun getResult() {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = moviesRepository.getPopularMovies()
               //val response = moviesRepository.getPopularMovies(Constant.apiKay)
               //val response = ApiManager.getInstance().getPopularMovies(Constant.apiKay)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        progressBarLiveData.value = true
                        resultLiveData.value = response.body()?.results
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressBarLiveData.value = true
                    showMessage.value = e.localizedMessage
                }
            }

        }

    }

}