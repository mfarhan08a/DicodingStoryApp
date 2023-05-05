package com.mfarhan08a.dicodingstoryapp.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.dicodingstoryapp.data.Repository
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val repository: Repository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun getDetailStory(token: String, id: String) = repository.getDetailStory(token, id)
}