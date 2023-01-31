package com.example.ds_movies.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.ds_movies.core.Constant
import com.example.ds_movies.data.ResultsItem
import com.example.ds_movies.data.api.ApiManager
import com.example.ds_movies.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesPopularViewModel : BaseViewModel() {
    val resultLiveData = MutableLiveData<MutableList<ResultsItem?>?>()
    var progressBarLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun getResult() {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = ApiManager.getInstance().getAllResults(Constant.apiKay)
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        progressBarLiveData.value = true
                        resultLiveData.value = response.body()?.results
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressBarLiveData.value = true
                    errorMessageLiveData.value = e.localizedMessage
                }
            }

        }

    }

}