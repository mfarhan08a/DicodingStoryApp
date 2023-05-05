package com.mfarhan08a.dicodingstoryapp.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mfarhan08a.dicodingstoryapp.data.Repository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun login(email: String, password: String) = repository.login(email, password)

    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun getLocaleSetting() = repository.getLocaleSetting().asLiveData(Dispatchers.IO)
}