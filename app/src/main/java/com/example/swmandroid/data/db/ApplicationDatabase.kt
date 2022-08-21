package com.example.swmandroid.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.swmandroid.data.db.dao.RecentSearchDao
import com.example.swmandroid.data.entity.RecentSearchEntity

@Database(
    entities = [RecentSearchEntity::class,],
    version = 1,
    exportSchema = false,
)

abstract class ApplicationDatabase : RoomDatabase(){

    companion object{
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun RecentSearchDao() : RecentSearchDao

}