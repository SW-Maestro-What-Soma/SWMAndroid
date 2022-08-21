package com.example.swmandroid.di

import android.content.Context
import androidx.room.Room
import com.example.swmandroid.data.db.ApplicationDatabase

fun provideDB(context : Context) : ApplicationDatabase =
    Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME).build()

fun provideRecentSearchDao(database : ApplicationDatabase) = database.RecentSearchDao()