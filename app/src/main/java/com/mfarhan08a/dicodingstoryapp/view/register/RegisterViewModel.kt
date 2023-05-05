package com.mfarhan08a.dicodingstoryapp.view.register

import androidx.lifecycle.ViewModel
import com.mfarhan08a.dicodingstoryapp.data.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.register(name, email, password)
}