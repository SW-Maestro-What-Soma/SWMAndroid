package com.example.swmandroid.data.repository.community

import com.example.swmandroid.data.db.dao.RecentSearchDao
import com.example.swmandroid.data.entity.RecentSearchEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RecentSearchRepository(
    private val recentSearchDao: RecentSearchDao,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getAllRecentSearch(category: String, techStack: String): List<RecentSearchEntity> = withContext(ioDispatcher) {
        recentSearchDao.getAll(category, techStack)
    }

    suspend fun insertRecentSearch(recentSearchEntity: RecentSearchEntity) = withContext(ioDispatcher) {
        recentSearchDao.insert(recentSearchEntity)
    }

    suspend fun removeRecentSearch(recentSearchEntity: RecentSearchEntity) = withContext(ioDispatcher) {
        recentSearchDao.delete(recentSearchEntity.category, recentSearchEntity.techStack, recentSearchEntity.recentSearch)
    }

    suspend fun clearRecentSearch(category: String, techStack: String) = withContext(ioDispatcher) {
        recentSearchDao.deleteAll(category, techStack)
    }

}