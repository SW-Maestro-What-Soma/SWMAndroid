package com.example.swmandroid.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swmandroid.data.entity.RecentSearchEntity
import com.example.swmandroid.data.repository.community.RecentSearchRepository
import kotlinx.coroutines.launch

class CommunityViewModel(
    private val recentSearchRepository: RecentSearchRepository,
) : ViewModel() {

    private val _recentSearchLiveData = MutableLiveData<List<RecentSearchEntity>>()
    val recentSearchLiveData: LiveData<List<RecentSearchEntity>> = _recentSearchLiveData
    private var recentSearchData = mutableListOf<RecentSearchEntity>()

    private val _category = MutableLiveData<String>()
    val category : LiveData<String> = _category

    private val _techStack = MutableLiveData<String>()
    val techStack : LiveData<String> = _techStack

    fun addRecentSearchData(recentSearchEntity: RecentSearchEntity) = viewModelScope.launch {
        recentSearchRepository.insertRecentSearch(recentSearchEntity)

        recentSearchData.add(recentSearchEntity)
        _recentSearchLiveData.value = recentSearchData
    }

    fun removeRecentSearchData(recentSearchEntity: RecentSearchEntity) = viewModelScope.launch {
        recentSearchRepository.removeRecentSearch(recentSearchEntity)

        recentSearchData.remove(recentSearchEntity)
        _recentSearchLiveData.value = recentSearchData
    }

    fun removeAllRecentSearchData(category: String, techStack: String) = viewModelScope.launch {
        recentSearchRepository.clearRecentSearch(category, techStack)

        recentSearchData = recentSearchData.filter { it.category != category && it.techStack != techStack }.toMutableList()
        _recentSearchLiveData.value = recentSearchData
    }

    fun getAllRecentSearchData(category : String, techStack : String) = viewModelScope.launch {
        val dataList = recentSearchRepository.getAllRecentSearch(category, techStack)

        recentSearchData.clear()
        recentSearchData = dataList.toMutableList()
        _recentSearchLiveData.value = recentSearchData
    }

    fun setCategory(category : String) {
        _category.value = category
    }

    fun setTechStack(techStack : String) {
        _techStack.value = techStack
    }

}