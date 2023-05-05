package com.mfarhan08a.dicodingstoryapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfarhan08a.dicodingstoryapp.data.Repository
import com.mfarhan08a.dicodingstoryapp.di.Injection
import com.mfarhan08a.dicodingstoryapp.view.detail.DetailViewModel
import com.mfarhan08a.dicodingstoryapp.view.login.LoginViewModel
import com.mfarhan08a.dicodingstoryapp.view.main.MainViewModel
import com.mfarhan08a.dicodingstoryapp.view.post.PostViewModel
import com.mfarhan08a.dicodingstoryapp.view.register.RegisterViewModel
import com.mfarhan08a.dicodingstoryapp.view.settings.SettingsViewModel

class ViewModelFactory private constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> return LoginViewModel(repository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> return MainViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> return RegisterViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> return DetailViewModel(repository) as T
            modelClass.isAssignableFrom(PostViewModel::class.java) -> return PostViewModel(repository) as T
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> return SettingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}