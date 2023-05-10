package com.mfarhan08a.dicodingstoryapp.view.post

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.dicodingstoryapp.data.Repository
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class PostViewModel(private val repository: Repository) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun addNewStory(token: String, file: MultipartBody.Part, description: String, location: Location?) =
        repository.addNewStory(token, file, description, location)
}