package com.example.ds_movies.Base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val progressBar = MutableLiveData<Boolean>()
    val showMessage = MutableLiveData<String>()
}