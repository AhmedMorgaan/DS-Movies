package com.example.ds_movies.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ds_movies.Constant
import com.example.ds_movies.api.ApiManager
import com.example.ds_movies.model.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

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


//        ApiManager.getInstance().getAllResults(Constant.apiKay)
//            .enqueue(object : Callback<MoviesResponse> {
//                override fun onResponse(
//                    call: Call<MoviesResponse>,
//                    response: Response<MoviesResponse>
//                ) {
//                    progressBarLiveData.value = true
//                    resultLiveData.value = response.body()?.results
//                }
//
//                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                    progressBarLiveData.value = true
//                    errorMessageLiveData.value = t.localizedMessage
//                }
//            })

}


