package com.mfarhan08a.dicodingstoryapp.view.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mfarhan08a.dicodingstoryapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: Repository): ViewModel() {
    fun getLocaleSetting() = repository.getLocaleSetting().asLiveData(Dispatchers.IO)

    fun saveLocaleSetting(localeName: String) {
        viewModelScope.launch {
            repository.saveLocaleSetting(localeName)
        }
    }
}