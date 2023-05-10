package com.mfarhan08a.dicodingstoryapp.data.local

import android.content.Context
import androidx.room.RoomDatabase
import com.mfarhan08a.dicodingstoryapp.data.model.RemoteKeys
import com.mfarhan08a.dicodingstoryapp.data.model.Story
import androidx.room.Database
import androidx.room.Room

@Database(
    entities = [Story::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: StoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StoryDatabase {
            if (INSTANCE == null) {
                synchronized(StoryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StoryDatabase::class.java,
                        "story_db"
                    ).build()
                }
            }
            return INSTANCE as StoryDatabase
        }
    }
}