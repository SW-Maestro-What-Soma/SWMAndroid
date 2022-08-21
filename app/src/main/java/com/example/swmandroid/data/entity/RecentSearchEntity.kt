package com.example.swmandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentSearchEntity(
    val category: String,
    val techStack: String,
    val recentSearch: String,

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
)