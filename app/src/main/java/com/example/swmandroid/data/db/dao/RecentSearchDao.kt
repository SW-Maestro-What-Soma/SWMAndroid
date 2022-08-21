package com.example.swmandroid.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swmandroid.data.entity.RecentSearchEntity

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM RecentSearchEntity WHERE category=:category AND techStack=:techStack")
    suspend fun getAll(category: String, techStack: String): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recentSearchEntity: RecentSearchEntity)

    @Query("DELETE FROM RecentSearchEntity WHERE category=:category AND techStack=:techStack AND recentSearch=:recentSearch")
    suspend fun delete(category: String, techStack: String, recentSearch: String)

    @Query("DELETE FROM RecentSearchEntity WHERE category=:category AND techStack=:techStack")
    suspend fun deleteAll(category: String, techStack: String)
}