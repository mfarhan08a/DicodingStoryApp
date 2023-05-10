package com.mfarhan08a.dicodingstoryapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class StoryResponse(

    @field:SerializedName("listStory")
    val listStory: List<Story>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class DetailStoryResponse(

    @field:SerializedName("story")
    val story: Story,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

@Entity("story")
data class Story(

    @PrimaryKey(false)
    @ColumnInfo("id")
    @field:SerializedName("id")
    val id: String,

    @ColumnInfo("photoUrl")
    @field:SerializedName("photoUrl")
    val photoUrl: String,

    @ColumnInfo("createdAt")
    @field:SerializedName("createdAt")
    val createdAt: String,

    @ColumnInfo("name")
    @field:SerializedName("name")
    val name: String,

    @ColumnInfo("description")
    @field:SerializedName("description")
    val description: String,

    @ColumnInfo("lon")
    @field:SerializedName("lon")
    val lon: Double?,

    @ColumnInfo("lat")
    @field:SerializedName("lat")
    val lat: Double?
)
