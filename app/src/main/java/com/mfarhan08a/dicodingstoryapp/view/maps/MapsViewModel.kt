package com.mfarhan08a.dicodingstoryapp.view.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.dicodingstoryapp.data.Repository
import kotlinx.coroutines.Dispatchers

class MapsViewModel(private val repository: Repository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun getAllStoriesWithLocation(token: String) = repository.getAllStoriesWithLocation(token)
}