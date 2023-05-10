package com.mfarhan08a.dicodingstoryapp.utils

import com.mfarhan08a.dicodingstoryapp.data.model.Story

object DataDummy {
    fun generateDummyListStory(): List<Story> {
        val storyItems: MutableList<Story> = arrayListOf()

        for (i in 0..10) {
            val story = Story(
                id = "$i",
                name = "user $i",
                description = "desc $i",
                photoUrl = "photo $i",
                createdAt = "date $i",
                lat = i + 0.1,
                lon = i + 0.1
            )
            storyItems.add(story)
        }

        return storyItems
    }
}