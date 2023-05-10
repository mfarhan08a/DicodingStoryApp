package com.mfarhan08a.dicodingstoryapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mfarhan08a.dicodingstoryapp.data.network.ApiConfig
import com.mfarhan08a.dicodingstoryapp.data.Repository
import com.mfarhan08a.dicodingstoryapp.data.local.StoryDatabase

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        val storyDatabase = StoryDatabase.getDatabase(context)
        return Repository.getInstance(apiService, context.dataStore, storyDatabase)
    }
}