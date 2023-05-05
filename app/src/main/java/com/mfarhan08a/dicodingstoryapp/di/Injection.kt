package com.mfarhan08a.dicodingstoryapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mfarhan08a.dicodingstoryapp.data.network.ApiConfig
import com.mfarhan08a.dicodingstoryapp.data.Repository

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService, context.dataStore)
    }
}